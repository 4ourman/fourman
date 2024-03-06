package org.fourman.sojuproject.domain.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequestDTO {

    private String u_nick_name;
    @NotNull
    private String c_content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;





}