package org.fourman.sojuproject.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequestDTO {

    private String p_title;

    private String p_content;

    private String category;

}
