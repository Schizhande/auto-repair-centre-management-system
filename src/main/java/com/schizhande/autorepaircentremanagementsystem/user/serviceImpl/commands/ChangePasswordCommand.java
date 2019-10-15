package com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordCommand {

    @NotNull(message = "User id is required")
    private Long userId;

    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Password is required")
    private String confirmPassword;

}
