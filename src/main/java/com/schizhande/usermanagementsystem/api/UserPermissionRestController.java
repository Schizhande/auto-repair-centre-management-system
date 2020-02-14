package com.schizhande.usermanagementsystem.api;


import com.schizhande.usermanagementsystem.service.UserPermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/user-permission")
public class UserPermissionRestController {

    private final UserPermissionService userPermissionService;

    public UserPermissionRestController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }
}
