package org.fourman.sojuproject.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequestDTO {

    private String pTitle;

    private String pContent;

    private String category;

}
