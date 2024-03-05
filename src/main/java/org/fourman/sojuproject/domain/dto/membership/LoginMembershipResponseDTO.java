package org.fourman.sojuproject.domain.dto.membership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMembershipResponseDTO {

    private Long member_id;

    private String m_email;

    private String m_password;

}
