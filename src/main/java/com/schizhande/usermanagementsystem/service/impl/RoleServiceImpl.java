package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.exceptions.RecordNotFoundException;
import com.schizhande.usermanagementsystem.dao.RoleRepository;
import com.schizhande.usermanagementsystem.model.Role;
import com.schizhande.usermanagementsystem.service.RoleService;
import com.schizhande.usermanagementsystem.service.request.CreateRoleRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

import static com.schizhande.usermanagementsystem.utils.BeanValidator.validate;

@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findById(Long roleId) {

        log.info("Fetching role with id {}", roleId);

        return roleRepository.findById(roleId)
                .orElseThrow(()->new RecordNotFoundException("Role with id " + roleId + " not found"));
    }

    @Override
    public Page<Role> findAll(Integer page, Integer size) {

        Page<Role> roles = roleRepository.findAll(PageRequest.of(page, size));

        if (roles.getTotalElements() == 0) {
            throw new RecordNotFoundException("No users found.");
        }

        log.info("-----> Roles "+ roles.getContent());
        return roles;
    }

    @Override
    public Role saveRole(CreateRoleRequest request) {
        validate(request);

        val role = Role.builder()
                .description(request.getDescription())
                .name(request.getName())
                .build();

        return roleRepository.save(role);
    }

    @Override
    public Role update(Long roleId, CreateRoleRequest request) {

        validate(request);

        val role = findById(roleId);

        role.setDescription(request.getDescription());
        role.setName(request.getName());

        return roleRepository.save(role);
    }

    @Override
    public Role deleteRole(Long roleId) {

        val role = findById(roleId);

        role.setDeleted(true);

        return roleRepository.save(role);
    }

    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
