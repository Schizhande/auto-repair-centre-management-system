package com.schizhande.usermanagementsystem.service.request;

import lombok.Data;

@Data
public class CreateUserRequest extends NewUserRequest{

    private String password;
}
