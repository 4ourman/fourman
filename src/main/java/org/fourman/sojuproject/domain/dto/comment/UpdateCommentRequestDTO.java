package org.fourman.sojuproject.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentRequestDTO {

    private String u_nick_name;
    private String c_content;
}
