package org.fourman.sojuproject.domain.dto.post;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fourman.sojuproject.domain.entity.Post;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadPostResponseDTO {

    private Long postId;

    @Column(name = "u_nickname")  // 속성명에 대한 매핑 추가
    private String unickname;

    private String category;

    @Column(name = "p_title")  // 속성명에 대한 매핑 추가
    private String ptitle;

    private String p_content;

    private LocalDateTime p_date;

    @Column(name = "view_count")
    private Long viewcount;

    @Builder
    public ReadPostResponseDTO(Long viewcount){
        this.viewcount = viewcount;
    }

    public Post toEntity(){
        return Post.builder()
                .viewcount(viewcount)
                .build();
    }
}
