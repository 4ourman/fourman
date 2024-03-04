package org.fourman.sojuproject.domain.dto.post;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequestDTO {

    private String u_nickname;

    private String category;

    private String p_title;

    private String p_content;

}
