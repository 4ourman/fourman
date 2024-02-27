package org.fourman.sojuproject.domain.dto.membership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMembershipRequestDTO {

    private String mName;

    private String mId;

    private String mPassword;

    private Long mPhone;

    private String mAddress;

    private String mNickName;

}
