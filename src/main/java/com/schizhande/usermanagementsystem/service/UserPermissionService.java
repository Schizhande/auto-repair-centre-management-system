package com.schizhande.usermanagementsystem.service;

import com.schizhande.usermanagementsystem.model.UserPermission;
import com.schizhande.usermanagementsystem.service.request.CreateUserPermissionRequest;
import com.schizhande.usermanagementsystem.service.request.UpdateUserPermissionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserPermissionService {

    List<UserPermission> createManyPermission(Set<String> createDto);

    Page<UserPermission> findAll(Pageable pageable, String search);
    
    Collection<UserPermission> findAll();

    UserPermission findById(Long id);

    UserPermission update(UpdateUserPermissionRequest request);

    Collection<UserPermission> findAllUnAssignedPermissions(Long id);

    Collection<UserPermission> findAllAssignedPermissions(Long groupId);
}
