package com.schizhande.usermanagementsystem.service.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserChangePasswordCommand extends ChangePasswordCommand {

    private String oldPassword;

}
