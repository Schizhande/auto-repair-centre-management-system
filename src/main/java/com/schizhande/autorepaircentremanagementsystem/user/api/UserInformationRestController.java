package com.schizhande.autorepaircentremanagementsystem.user.api;

import com.schizhande.autorepaircentremanagementsystem.user.model.User;
import com.schizhande.autorepaircentremanagementsystem.user.service.UserInformationService;
import com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands.UpdateUserInformationRequest;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("v1/user-information")
public class UserInformationRestController {

    private final UserInformationService userInformationService;

    public UserInformationRestController(UserInformationService userInformationService) {
        this.userInformationService = userInformationService;
    }

    @GetMapping("/my-account")
    public User getUserInformation(Principal principal) {

        if(isNull(principal)){
           throw  new IllegalStateException("Only logged in user can perform this operation");
        }

        return userInformationService.findByUsername(principal.getName());
    }

    @PostMapping("/my-account")
    public User updateUserAccount(Principal principal,
                                  @RequestBody UpdateUserInformationRequest updateUserInformationRequest){


        updateUserInformationRequest.setUsername("sheldon");
        return userInformationService.updateUserAccount(updateUserInformationRequest);
    }

    @PutMapping("/{userId}")
    public User updateUserInformation(@PathVariable Long userId,
                                      @RequestBody UpdateUserInformationRequest updateUserInformationRequest ){
        return userInformationService.updateUserInformation(userId, updateUserInformationRequest);
    }

}
