package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.dao.UserPermissionRepository;
import com.schizhande.usermanagementsystem.dao.jpa.CustomSpecificationTemplateImplBuilder;
import com.schizhande.usermanagementsystem.exceptions.RecordNotFoundException;
import com.schizhande.usermanagementsystem.model.UserPermission;
import com.schizhande.usermanagementsystem.service.RoleService;
import com.schizhande.usermanagementsystem.service.UserPermissionService;
import com.schizhande.usermanagementsystem.service.request.UpdateUserPermissionRequest;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.schizhande.usermanagementsystem.utils.BeanValidator.validate;
import static java.util.stream.Collectors.toSet;

public class UserPermissionServiceImpl implements UserPermissionService {

    private final UserPermissionRepository userPermissionRepository;

    private final RoleService roleService;

    public UserPermissionServiceImpl(
            UserPermissionRepository userPermissionRepository, RoleService roleService) {
        this.userPermissionRepository = userPermissionRepository;
        this.roleService = roleService;
    }

    @Override
    public List<UserPermission> createManyPermission(Set<String> createDto) {

        val allPermissions = userPermissionRepository.findAll()
                .parallelStream()
                .map(UserPermission::getAuthority)
                .collect(toSet());

        val newPermissions = createDto
                .parallelStream()
                .filter(permission -> !allPermissions.contains(permission))
                .map(UserPermission::create)
                .collect(toSet());

        return userPermissionRepository.saveAll(newPermissions);
    }

    @Override
    public Page<UserPermission> findAll(Pageable pageable, String search) {
        if (Objects.isNull(search)) {
            return userPermissionRepository.findAll(pageable);
        } else {
            Specification<UserPermission> spec = new CustomSpecificationTemplateImplBuilder<UserPermission>()
                    .buildSpecification(search);
            return userPermissionRepository.findAll(spec, pageable);
        }
    }

    @Override
    public Collection<UserPermission> findAll() {
        return userPermissionRepository.findAll();
    }

    @Override
    public UserPermission findById(Long id) {
        return userPermissionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User permission with id " + id + " not found"));
    }

    @Override
    public UserPermission update(UpdateUserPermissionRequest updateDto) {

        validate(updateDto);
        val userPermission = findById(updateDto.getId());
        userPermission.setDescription(updateDto.getPermissionDescription());
        return userPermissionRepository.save(userPermission);
    }

    @Override
    public Collection<UserPermission> findAllUnAssignedPermissions(Long id) {

        val allAssignedPermissions = findAllAssignedPermissions(id);

        return userPermissionRepository.findAll()
                .parallelStream()
                .filter(userPermission -> !allAssignedPermissions.contains(userPermission))
                .collect(toSet());
    }

    @Override
    public Collection<UserPermission> findAllAssignedPermissions(Long groupId) {
        val role = roleService.findById(groupId);
        return role.getPermissions();
    }

}
