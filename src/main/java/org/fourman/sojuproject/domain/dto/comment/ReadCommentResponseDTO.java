package org.fourman.sojuproject.domain.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadCommentResponseDTO {

    private Long commentId;

    private String u_nick_name;

    private String c_content;

    private LocalDateTime cRegDate;

    private LocalDateTime cModDate;

    public ReadCommentResponseDTO(Long commentId, String u_nick_name, String c_content, LocalDateTime cRegDate) {

        this.commentId = commentId;
        this.u_nick_name = u_nick_name;
        this.c_content = c_content;
        this.cRegDate = cRegDate;


    }

}
