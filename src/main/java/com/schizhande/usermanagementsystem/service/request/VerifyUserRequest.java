package com.schizhande.usermanagementsystem.service.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VerifyUserRequest {

    @NotBlank(message = "Token ie required")
    private String token;

}
