package com.schizhande.autorepaircentremanagementsystem.user.model;


import com.schizhande.autorepaircentremanagementsystem.commons.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_INFORMATION")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInformation extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private Address address;

}
