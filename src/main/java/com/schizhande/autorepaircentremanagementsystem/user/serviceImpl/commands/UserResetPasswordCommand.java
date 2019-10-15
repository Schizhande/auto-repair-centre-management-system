package com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
public class UserResetPasswordCommand {

    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Password is required")
    private String confirmPassword;

}
