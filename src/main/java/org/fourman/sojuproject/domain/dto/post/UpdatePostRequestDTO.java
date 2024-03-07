package org.fourman.sojuproject.domain.dto.post;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePostRequestDTO {

    @Column(name = "p_title")  // 속성명에 대한 매핑 추가
    private String ptitle;

    private String p_content;

    private String category;

}
