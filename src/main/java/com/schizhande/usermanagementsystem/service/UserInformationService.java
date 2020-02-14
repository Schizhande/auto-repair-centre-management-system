package com.schizhande.usermanagementsystem.service;

import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.service.request.UpdateUserInformationRequest;

public interface UserInformationService {
    User findByUsername(String name);

    User updateUserAccount(UpdateUserInformationRequest updateUserInformationRequest);

    User updateUserInformation(Long userId, UpdateUserInformationRequest updateUserInformationRequest);
}
