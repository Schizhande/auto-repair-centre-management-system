package com.schizhande.autorepaircentremanagementsystem.user.service;

import com.schizhande.autorepaircentremanagementsystem.user.model.User;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.ChangePasswordCommand;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UserChangePasswordCommand;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UserResetPasswordCommand;
import org.springframework.web.context.request.WebRequest;

public interface UserPasswordService {
    User changePassword(ChangePasswordCommand changePasswordCommand);

    User userChangePassword(UserChangePasswordCommand userChangePasswordCommand);

    void resetPasswordRequest(WebRequest webRequest, String email);

    User resetPassword(UserResetPasswordCommand userResetPasswordCommand);
}
