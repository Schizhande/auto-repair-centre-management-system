package com.schizhande.usermanagementsystem.service;

import com.schizhande.usermanagementsystem.model.Role;
import com.schizhande.usermanagementsystem.model.UserPermission;
import com.schizhande.usermanagementsystem.service.request.AssignUserPermissionRequest;
import com.schizhande.usermanagementsystem.service.request.CreateRoleRequest;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface RoleService {
    Role findById(Long roleId);

    Page<Role> findAll(Integer page, Integer size);

    Role saveRole(CreateRoleRequest request);

    Role update(Long roleId, CreateRoleRequest request);

    Role deleteRole(Long roleId);

    Collection<Role> findAll();

    Collection<UserPermission> assignPermissions(AssignUserPermissionRequest request);

    Collection<UserPermission> unAssignPermissions(AssignUserPermissionRequest request);
}
