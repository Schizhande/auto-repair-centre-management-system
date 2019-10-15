package com.schizhande.autorepaircentremanagementsystem.user.service;

import com.schizhande.autorepaircentremanagementsystem.commons.service.BaseService;
import com.schizhande.autorepaircentremanagementsystem.user.model.UserPermission;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.CreateUserPermissionRequest;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UpdateUserPermissionRequest;

public interface UserPermissionService extends BaseService<UserPermission , CreateUserPermissionRequest,
        UpdateUserPermissionRequest> {
}
