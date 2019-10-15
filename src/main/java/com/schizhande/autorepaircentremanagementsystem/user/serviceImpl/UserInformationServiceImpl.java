package com.schizhande.autorepaircentremanagementsystem.user.serviceImpl;

import com.schizhande.autorepaircentremanagementsystem.commons.exceptions.RecordNotFoundException;
import com.schizhande.autorepaircentremanagementsystem.user.dao.UserInformationRepository;
import com.schizhande.autorepaircentremanagementsystem.user.dao.UserRepository;
import com.schizhande.autorepaircentremanagementsystem.user.model.User;
import com.schizhande.autorepaircentremanagementsystem.user.model.UserInformation;
import com.schizhande.autorepaircentremanagementsystem.user.service.UserInformationService;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UpdateUserInformationRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.schizhande.autorepaircentremanagementsystem.commons.validations.BeanValidator.validate;
import static com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UpdateUserInformationRequest.buildFromRequest;

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
