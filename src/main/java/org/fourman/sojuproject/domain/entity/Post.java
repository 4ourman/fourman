package org.fourman.sojuproject.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fourman.sojuproject.reposittory.PostRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue
    private Long postId;

    @Column(name = "u_nickname")  // 속성명에 대한 매핑 추가
    private String unickname;

    private String category;

    @Column(name = "p_title")  // 속성명에 대한 매핑 추가
    private String ptitle;

    private String p_content;

    private LocalDateTime p_date;

    public void update(String ptitle, String p_content, String category) {
        this.ptitle = ptitle;
        this.p_content = p_content;
        this.category = category;
    }

}
