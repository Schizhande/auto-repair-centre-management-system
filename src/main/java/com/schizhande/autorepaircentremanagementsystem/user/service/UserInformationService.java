package com.schizhande.autorepaircentremanagementsystem.user.service;

import com.schizhande.autorepaircentremanagementsystem.user.model.User;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UpdateUserInformationRequest;

public interface UserInformationService {
    User findByUsername(String name);

    User updateUserAccount(UpdateUserInformationRequest updateUserInformationRequest);

    User updateUserInformation(Long userId, UpdateUserInformationRequest updateUserInformationRequest);
}
