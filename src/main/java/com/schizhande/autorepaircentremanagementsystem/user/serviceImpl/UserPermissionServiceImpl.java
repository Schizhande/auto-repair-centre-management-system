package com.schizhande.autorepaircentremanagementsystem.user.serviceImpl;

import com.schizhande.autorepaircentremanagementsystem.commons.jpa.BaseRepository;
import com.schizhande.autorepaircentremanagementsystem.commons.service.BaseServiceImpl;
import com.schizhande.autorepaircentremanagementsystem.user.model.UserPermission;
import com.schizhande.autorepaircentremanagementsystem.user.service.UserPermissionService;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.CreateUserPermissionRequest;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UpdateUserPermissionRequest;

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
