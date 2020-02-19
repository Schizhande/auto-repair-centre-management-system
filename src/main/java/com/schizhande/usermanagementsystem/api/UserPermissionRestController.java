package com.schizhande.usermanagementsystem.api;


import com.schizhande.usermanagementsystem.model.UserPermission;
import com.schizhande.usermanagementsystem.service.UserPermissionService;
import com.schizhande.usermanagementsystem.service.request.UpdateUserPermissionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/user-permission")
public class UserPermissionRestController {

    private final UserPermissionService userPermissionService;

    public UserPermissionRestController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @GetMapping(value = "/", params = {"page", "size"})
    public Page<UserPermission> getAllPermissions(
            @PageableDefault(sort = {"authority"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "search", required = false) String search) {
        return userPermissionService.findAll(pageable, search);
    }

    @GetMapping("/all")
    public Collection<UserPermission> getAllPermissions(){
        return userPermissionService.findAll();
    }

    @GetMapping("/{id}")
    public UserPermission getUserPermission(@PathVariable Long id) {
        return userPermissionService.findById(id);
    }

    @PutMapping("/{id}")
    public UserPermission updatePermission(@PathVariable Long id, @RequestBody UpdateUserPermissionRequest request) {
        request.setId(id);
        return userPermissionService.update(request);
    }

}
