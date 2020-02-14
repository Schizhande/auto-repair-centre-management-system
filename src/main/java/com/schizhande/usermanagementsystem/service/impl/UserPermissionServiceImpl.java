package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.dao.jpa.BaseRepository;
import com.schizhande.usermanagementsystem.model.UserPermission;
import com.schizhande.usermanagementsystem.service.UserPermissionService;
import com.schizhande.usermanagementsystem.service.request.CreateUserPermissionRequest;
import com.schizhande.usermanagementsystem.service.request.UpdateUserPermissionRequest;

public class UserPermissionServiceImpl extends BaseServiceImpl<UserPermission, CreateUserPermissionRequest ,
        UpdateUserPermissionRequest> implements UserPermissionService {


    public UserPermissionServiceImpl(BaseRepository<UserPermission> repository) {
        super(repository);
    }

    @Override
    public UserPermission create(CreateUserPermissionRequest createDto) {



        return null;
    }

    @Override
    public UserPermission update(UpdateUserPermissionRequest updateDto) {
        return null;
    }

    @Override
    public void pseudoDelete(Long id) {

    }
}
