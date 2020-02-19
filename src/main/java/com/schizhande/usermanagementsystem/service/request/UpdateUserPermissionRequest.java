package com.schizhande.usermanagementsystem.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserPermissionRequest {

    @NotNull(message = "Permission id is required")
    private Long id;

    @NotNull(message = "Permission description is required")
    private String permissionDescription;

}
