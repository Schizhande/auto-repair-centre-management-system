package com.schizhande.usermanagementsystem.api;

import com.schizhande.usermanagementsystem.model.User;
import com.schizhande.usermanagementsystem.service.UserPasswordService;
import com.schizhande.usermanagementsystem.service.request.ChangePasswordCommand;
import com.schizhande.usermanagementsystem.service.request.UserChangePasswordCommand;
import com.schizhande.usermanagementsystem.service.request.UserResetPasswordCommand;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;

import static java.util.Objects.isNull;

@RestController
public class UserPasswordRestController {

    private final UserPasswordService userPasswordService;

    public UserPasswordRestController(UserPasswordService userPasswordService) {
        this.userPasswordService = userPasswordService;
    }

    @PostMapping("/v1/admin/users/{id}/change-password")
    public User changeUserPassword(@RequestBody ChangePasswordCommand changePasswordCommand,
                                   @RequestParam(value = "username", required = false) String username,
                                   @PathVariable Long id) {

        changePasswordCommand.setUserId(id);

        changePasswordCommand.setUsername(username);

        return userPasswordService.changePassword(changePasswordCommand);

    }

    @PostMapping("/v1/users/change-password")
    public User changeUserPassword(@RequestBody UserChangePasswordCommand userChangePasswordCommand, Principal principal) {

        if (isNull(principal))
            throw new AccessDeniedException("Yoo have to be logged in to perform this operation");

        userChangePasswordCommand.setUsername(principal.getName());

        return userPasswordService.userChangePassword(userChangePasswordCommand);
    }

    @PostMapping("/v1/users/forgot-password")
    public void changeUserPassword(@RequestParam String email, WebRequest webRequest) {

        userPasswordService.resetPasswordRequest(webRequest, email);

    }

    @PostMapping("/v1/users/reset-password")
    public User resetPassword(@RequestBody UserResetPasswordCommand userResetPasswordCommand) {
        return userPasswordService.resetPassword(userResetPasswordCommand);

    }

}
