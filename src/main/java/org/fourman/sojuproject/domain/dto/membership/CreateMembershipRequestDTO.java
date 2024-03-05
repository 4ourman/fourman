package org.fourman.sojuproject.domain.dto.membership;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMembershipRequestDTO {

    private String m_name;

    private String m_email;

    private String m_password;

    private String m_phone;

    private String m_address;

    private String m_nick_name;

}
