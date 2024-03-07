package org.fourman.sojuproject.domain.dto.post;

import jakarta.persistence.Column;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequestDTO {

    @Column(name = "u_nickname")  // 속성명에 대한 매핑 추가
    private String unickname;

    private String category;

    @Column(name = "p_title")  // 속성명에 대한 매핑 추가
    private String ptitle;

    private String p_content;

}
