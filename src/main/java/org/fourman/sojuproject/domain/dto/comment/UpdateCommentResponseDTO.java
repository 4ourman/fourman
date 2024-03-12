package org.fourman.sojuproject.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentResponseDTO {

    private Long commentId;

    private String u_nick_name;

    private String c_content;

    private LocalDateTime cRegDate;

    private LocalDateTime cModDate;


}
