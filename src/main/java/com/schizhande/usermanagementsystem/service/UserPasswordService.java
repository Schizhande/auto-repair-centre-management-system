package com.schizhande.usermanagementsystem.service;

import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.service.request.ChangePasswordCommand;
import com.schizhande.usermanagementsystem.service.request.UserChangePasswordCommand;
import com.schizhande.usermanagementsystem.service.request.UserResetPasswordCommand;
import org.springframework.web.context.request.WebRequest;

public interface UserPasswordService {
    User changePassword(ChangePasswordCommand changePasswordCommand);

    User userChangePassword(UserChangePasswordCommand userChangePasswordCommand);

    void resetPasswordRequest(WebRequest webRequest, String email);

    User resetPassword(UserResetPasswordCommand userResetPasswordCommand);
}
