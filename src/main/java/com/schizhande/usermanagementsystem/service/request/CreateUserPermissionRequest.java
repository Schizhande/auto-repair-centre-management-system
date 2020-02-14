package com.schizhande.usermanagementsystem.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateUserPermissionRequest {

    @NotNull(message = "Role id is required")
    private Long roleId;

    @NotNull(message = "Permission description is required")
    private String permissionDescription;

    @NotNull
    private String permission;

    @NotNull(message = "Display name is required")
    private String displayName;

}
