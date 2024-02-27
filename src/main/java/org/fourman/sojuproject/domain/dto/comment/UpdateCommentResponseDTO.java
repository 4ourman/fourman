package org.fourman.sojuproject.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentResponseDTO {

    private Long commentId;

    private String uNickName;

    private String cContent;

}
