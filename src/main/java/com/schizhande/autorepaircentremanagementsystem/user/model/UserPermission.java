package com.schizhande.autorepaircentremanagementsystem.user.model;

import com.schizhande.autorepaircentremanagementsystem.commons.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Data
@Entity
@Table(name = "USER_PERMISSIONS")
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

    @JoinColumn(name = "role_id", nullable = false)
    @ManyToOne
    private Role role;

    @Column(nullable = false)
    private String authority;

    @Column
    private String description;
}
