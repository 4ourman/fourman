package org.fourman.sojuproject.domain.dto.picture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePictureResponseDTO {

    private Long pictureId;
    private String pAddress;

}
