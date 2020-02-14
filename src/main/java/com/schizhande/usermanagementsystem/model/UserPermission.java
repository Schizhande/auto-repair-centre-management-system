package com.schizhande.usermanagementsystem.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Data
@Entity
@Table(name = "permission")
@Where(clause = "deleted=0")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserPermission extends BaseEntity {
	
	@Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String authority;

    @Column
    private String description;
}
