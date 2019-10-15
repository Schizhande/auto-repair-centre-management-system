package com.schizhande.autorepaircentremanagementsystem.user.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

    @Column
    @NotBlank(message = "Home address is required")
    private String homeAddress;

    @Column
    @NotBlank(message = "Postal address is required")
    private String postalAddress;

    @Column
    @NotBlank(message = "City address is required")
    private String city;

    @Column
    @NotBlank(message = "Country is required")
    private String country;

    @Column
    @NotBlank(message = "State is required")
    private String state;
}
