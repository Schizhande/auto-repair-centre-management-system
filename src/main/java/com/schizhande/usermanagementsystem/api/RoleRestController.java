package com.schizhande.usermanagementsystem.api;


import com.schizhande.usermanagementsystem.exceptions.ErrorBody;
import com.schizhande.usermanagementsystem.exceptions.Success;
import com.schizhande.usermanagementsystem.model.Role;
import com.schizhande.usermanagementsystem.service.RoleService;
import com.schizhande.usermanagementsystem.service.request.CreateRoleRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/roles")
public class RoleRestController {
    
    private final RoleService roleService;


    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody CreateRoleRequest request ) {
        Role role = roleService.saveRole(request);
        if (role == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to save role.").build();
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(role);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getUserById(@PathVariable Long roleId){
        Role role = roleService.findById(roleId);
        return ResponseEntity.ok(role);
    }

    @GetMapping( params = {"page", "size"})
    public Page<Role> getAllRoles(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return roleService.findAll(page,size);
    }

    @GetMapping("/all")
    public Collection<Role> getAllRoles(){
        return roleService.findAll();
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable Long roleId, @RequestBody CreateRoleRequest request){
        Role role = roleService.update(roleId, request);
        if (role == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to update role.").build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId){
        Role role = roleService.deleteRole(roleId);
        if (role == null) {
            ErrorBody response = ErrorBody.builder().message("Failed to delete role.").build();
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }
        Success response = Success.builder().Id(role.getId()).message("Role deletion was successful.").build();
        return ResponseEntity.ok(response);
    }

}
