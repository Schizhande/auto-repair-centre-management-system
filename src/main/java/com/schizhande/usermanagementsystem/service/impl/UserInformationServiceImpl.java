package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.exceptions.RecordNotFoundException;
import com.schizhande.usermanagementsystem.dao.UserInformationRepository;
import com.schizhande.usermanagementsystem.dao.UserRepository;
import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.model.UserInformation;
import com.schizhande.usermanagementsystem.service.UserInformationService;
import com.schizhande.usermanagementsystem.service.request.UpdateUserInformationRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.schizhande.usermanagementsystem.utils.BeanValidator.validate;
import static com.schizhande.usermanagementsystem.service.request.UpdateUserInformationRequest.buildFromRequest;

@Slf4j
@Transactional
public class UserInformationServiceImpl implements UserInformationService {

    private final UserRepository userRepository;

    private final UserInformationRepository userInformationRepository;

    public UserInformationServiceImpl(UserRepository userRepository, UserInformationRepository userInformationRepository) {
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
    }

    @Override
    public User findByUsername(String name) {

        return userRepository.findByUsername(name)
                .orElseThrow(()->new RecordNotFoundException("User information for username "+ name + "not found"));
    }

    @Override
    public User updateUserAccount(UpdateUserInformationRequest updateUserInformationRequest) {

        validate(updateUserInformationRequest);

        val user = findByUsername(updateUserInformationRequest.getUsername());

        return update(updateUserInformationRequest, user);
    }

    @Override
    public User updateUserInformation(Long userId, UpdateUserInformationRequest updateUserInformationRequest) {

        val user = userRepository.findById(userId)
                .orElseThrow(()->new RecordNotFoundException("User with id :" +userId+ "not found"));

        return update(updateUserInformationRequest, user);
    }

    private User update(UpdateUserInformationRequest updateUserInformationRequest, User user) {
        val userInformation = Optional.ofNullable(user.getUserInformation())
                .orElse(new UserInformation());

        buildFromRequest(userInformation, updateUserInformationRequest);

        val persistedUserInformation = userInformationRepository.save(userInformation);

        user.setUserInformation(persistedUserInformation);

        return userRepository.save(user);
    }

}
