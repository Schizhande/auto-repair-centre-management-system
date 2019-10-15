package com.schizhande.autorepaircentremanagementsystem.user.model;

import com.schizhande.autorepaircentremanagementsystem.commons.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "TOKENS")
@Where(clause = "deleted=0")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Token extends BaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String token;

        @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
        @JoinColumn(nullable = false, name = "user_id")
        private User user;

        private Date expiryDate;

    public Token(String token, User user) {
        this.token=token;
        this.user= user;
    }

    public void calculateExpiryDate(int expiryTimeInMinutes) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Timestamp(cal.getTime().getTime()));
            cal.add(Calendar.MINUTE, expiryTimeInMinutes);
            this.expiryDate = new Date(cal.getTime().getTime());
        }

        // standard constructors, getters and setters

}
