package com.schizhande.autorepaircentremanagementsystem.user.service;

import com.schizhande.autorepaircentremanagementsystem.user.model.Role;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.CreateRoleRequest;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface RoleService {
    Role findById(Long roleId);

    Page<Role> findAll(Integer page, Integer size);

    Role saveRole(CreateRoleRequest request);

    Role update(Long roleId, CreateRoleRequest request);

    Role deleteRole(Long roleId);

    Collection<Role> findAll();
}
