package com.schizhande.usermanagementsystem.service;

import com.schizhande.usermanagementsystem.model.UserPermission;
import com.schizhande.usermanagementsystem.service.request.CreateUserPermissionRequest;
import com.schizhande.usermanagementsystem.service.request.UpdateUserPermissionRequest;

public interface UserPermissionService extends BaseService<UserPermission , CreateUserPermissionRequest,
        UpdateUserPermissionRequest> {
}
