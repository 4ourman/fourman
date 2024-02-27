package org.fourman.sojuproject.domain.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostResponseDTO {

    private Long postId;

    private String uNickname;

    private Long fId;

    private String category;

    private Long cId;

    private String pTitle;

    private String pContent;

    private Long pViews;

    private LocalDateTime pDate;

}
