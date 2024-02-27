package org.fourman.sojuproject.domain.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminResponseDTO {

    private Long adminId;

    private String aId;

    private String aPassword;

}
