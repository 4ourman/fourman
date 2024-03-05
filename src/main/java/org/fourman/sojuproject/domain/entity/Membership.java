package org.fourman.sojuproject.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Membership {

    @Id
    @GeneratedValue
    private Long memberId;

    private String mName;

    private String mEmail;

    private String mPassword;

    private String mPhone;

    private String mAddress;

    private String mNickName;

    private boolean isLoggedIn;

}
