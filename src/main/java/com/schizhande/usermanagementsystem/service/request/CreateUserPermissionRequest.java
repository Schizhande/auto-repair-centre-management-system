package com.schizhande.usermanagementsystem.service.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateUserPermissionRequest {

    @NotNull
    private String permission;

}
