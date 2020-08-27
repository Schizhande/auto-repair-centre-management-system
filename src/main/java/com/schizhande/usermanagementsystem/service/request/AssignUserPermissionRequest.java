package com.schizhande.usermanagementsystem.service.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
public class AssignUserPermissionRequest {

    private Set<Long> authoritiesId;

    @NotNull(message = "Group id should be provided")
    private Long groupId;
}
