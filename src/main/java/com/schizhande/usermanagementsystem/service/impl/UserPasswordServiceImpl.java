package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.exceptions.InvalidRequestException;
import com.schizhande.usermanagementsystem.exceptions.RecordNotFoundException;
import com.schizhande.usermanagementsystem.dao.TokenRepository;
import com.schizhande.usermanagementsystem.dao.UserRepository;
import com.schizhande.usermanagementsystem.events.OnChangePasswordEvent;
import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.service.UserPasswordService;
import com.schizhande.usermanagementsystem.service.request.ChangePasswordCommand;
import com.schizhande.usermanagementsystem.service.request.UserChangePasswordCommand;
import com.schizhande.usermanagementsystem.service.request.UserResetPasswordCommand;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.NotNull;

import java.util.Calendar;

import static com.schizhande.usermanagementsystem.utils.BeanValidator.validate;


@Slf4j
public class UserPasswordServiceImpl implements UserPasswordService {

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserPasswordServiceImpl(TokenRepository tokenRepository,
                                   UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   ApplicationEventPublisher applicationEventPublisher) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public User changePassword(ChangePasswordCommand changePasswordCommand) {

        validate(changePasswordCommand);

        val userId = changePasswordCommand.getUserId();

        User user = findUserById(userId);

        user.setPassword(passwordEncoder.encode(changePasswordCommand.getPassword()));

        return userRepository.save(user);

    }

    private User findUserById(@NotNull(message = "User id is required") Long userId) {
        return userRepository.findById(userId)
                    .orElseThrow(()->new RecordNotFoundException("User with id " + userId + "not found"));
    }

    @Transactional
    public User userChangePassword(UserChangePasswordCommand userChangePasswordCommand) {

        validate(userChangePasswordCommand);

        val username = userChangePasswordCommand.getUsername();

        val user =userRepository.findByUsername(username)
                .orElseThrow(()->new RecordNotFoundException("User with username "+ username + "not found"));

        val oldPassword = user.getPassword();

        if (!(passwordEncoder.matches(userChangePasswordCommand.getOldPassword(), oldPassword))) {
            throw new InvalidRequestException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(userChangePasswordCommand.getPassword()));

        return userRepository.save(user);

    }

    @Transactional
    public User resetPassword(UserResetPasswordCommand userResetPasswordCommand) {

        val token = tokenRepository.findByToken(userResetPasswordCommand.getToken())
                .orElseThrow(()->new InvalidRequestException("Invalid token"));

        Calendar cal = Calendar.getInstance();
        if ((token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw  new InvalidRequestException("Verification token expired");
        }

        val user = token.getUser();

        tokenRepository.save(token);

        user.setPassword(passwordEncoder.encode(userResetPasswordCommand.getPassword()));

        return userRepository.save(user);

    }

    public void resetPasswordRequest(WebRequest webRequest , String email) {


        val user = userRepository.findByEmail(email)
                .orElseThrow(()->new RecordNotFoundException("Email "+ email + "not found"));

        try {
            String appUrl = webRequest.getContextPath();
            applicationEventPublisher.publishEvent(new OnChangePasswordEvent(user, webRequest.getLocale(), appUrl));
        } catch (Exception ex) {

            ex.printStackTrace();;

            throw new InvalidRequestException("Fail to send verification token due to" + ex.getMessage());
        }
    }

}
