package com.schizhande.autorepaircentremanagementsystem.user.serviceImpl.commands;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schizhande.autorepaircentremanagementsystem.user.model.Address;
import com.schizhande.autorepaircentremanagementsystem.user.model.UserInformation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Slf4j
public class UpdateUserInformationRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Valid
    private Address address;

    @NotBlank(message = "Username is required")
    private String username;

    public static UserInformation buildFromRequest(UserInformation userInformation,
                                            UpdateUserInformationRequest updateUserInformationRequest){
        try{
            return new  ObjectMapper().setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP))
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .updateValue(userInformation, updateUserInformationRequest);
        }catch (JsonMappingException e) {
            log.error("---> Failed to map user information Objects ", e);
            throw new IllegalStateException("Failed to map the objects");
        }

    }
}
