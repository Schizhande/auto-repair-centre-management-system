package com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands;

import lombok.Data;

@Data
public class CreateUserRequest extends NewUserRequest{

    private String password;
}
