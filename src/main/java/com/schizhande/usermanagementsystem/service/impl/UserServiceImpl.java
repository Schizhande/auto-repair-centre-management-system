package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.exceptions.InvalidRequestException;
import com.schizhande.usermanagementsystem.exceptions.RecordExistsException;
import com.schizhande.usermanagementsystem.exceptions.RecordNotFoundException;
import com.schizhande.usermanagementsystem.dao.RoleRepository;
import com.schizhande.usermanagementsystem.dao.TokenRepository;
import com.schizhande.usermanagementsystem.dao.UserRepository;
import com.schizhande.usermanagementsystem.events.OnUserCreateEvent;
import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.service.UserService;
import com.schizhande.usermanagementsystem.service.request.NewUserRequest;
import com.schizhande.usermanagementsystem.service.request.VerifyUserRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Collection;

import static java.util.Objects.isNull;

import static com.schizhande.usermanagementsystem.utils.BeanValidator.validate;

@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final TokenRepository tokenRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository,
                           TokenRepository tokenRepository,
                           ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public User saveUser(NewUserRequest request, WebRequest webRequest) {

        validate(request);

        val user = buildUser(request);

        userRepository.findByUsername(user.getUsername())
                .ifPresent(user1 -> {
                    throw new RecordExistsException("Username " + user.getUsername() + " is available,please choose " +
                            "another");
                });
        val role = roleRepository.findById(request.getRoleId())
                .orElseThrow(()->new RecordNotFoundException("Role with id :" + request.getRoleId() + " not found"));

        user.setRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        val registeredUser = userRepository.save(user);

        try {
            String appUrl = webRequest.getContextPath();
            log.info("-----------> Users "+ registeredUser);
            applicationEventPublisher.publishEvent(new OnUserCreateEvent(registeredUser, webRequest.getLocale(), appUrl));
        } catch (Exception ex) {

            ex.printStackTrace();;

            throw new InvalidRequestException("Fail to send verification token due to " + ex.getMessage());
        }

        return registeredUser;
    }

    @Override
    public User findById(Long userId) {

        log.info("Fetching user with id {}", userId);

        return userRepository.findById(userId)
                .orElseThrow(()->new RecordNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public Page<User> findAll(Integer page, Integer size) {

        Page<User> users = userRepository.findAll(PageRequest.of(page, size));

        if (users.getTotalElements() == 0) {
            throw new RecordNotFoundException("No users found.");
        }

        log.info("-----> Users "+ users.getContent());
        return users;
    }

    @Override
    public User update(Long userId, NewUserRequest request) {

        validate(request);

        val user = findById(userId);

        val role = roleRepository.findById(request.getRoleId())
                .orElseThrow(()->new RecordNotFoundException("Role with id :" + request.getRoleId() + "not found"));

        user.setRole(role);
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Long userId) {

        val user = findById(userId);

        user.setDeleted(true);

        return userRepository.save(user);
    }

    @Override
    public Page<User> findByRole(Long roleId, Integer page, Integer size) {

        val role = roleRepository.findById(roleId)
                .orElseThrow(()->new RecordNotFoundException("Role with id :" + roleId + " not found"));

        Page<User> users = userRepository.findByRole(role, PageRequest.of(page, size));

        if (users.getTotalElements() == 0) {
            throw new RecordNotFoundException("No users found.");
        }
        log.info("-----> Users "+ users.getContent());
        return users;
    }

    @Override
    public User enableUser(Long userId, boolean enable) {

        if(isNull(userId)){
            throw new InvalidRequestException("User id is required");
        }
        if(isNull(enable)){
            throw new InvalidRequestException("Enable decision is required");
        }

        val user = findById(userId);

        user.setEnable(enable);

        return userRepository.save(user);
    }

    @Override
    public User verifyUser(VerifyUserRequest request) {
        validate(request);

        val token = request.getToken();

        val retrievedToken = tokenRepository.findByToken(token)
                .orElseThrow(()->new InvalidRequestException("Invalid token"));

        Calendar cal = Calendar.getInstance();
        if ((retrievedToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw  new InvalidRequestException("Verification token expired");
        }

        val user = retrievedToken.getUser();

        user.setEnable(true);

        return userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    private User buildUser(NewUserRequest request){

        val password = PasswordGenerator.generatePassword(5);
        log.info("----> User password: {}", password);

        val user = User.builder()
                .password(password)
                .username(request.getUsername())
                .email(request.getEmail())
                .build();
        return user;
    }

}
