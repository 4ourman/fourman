package org.fourman.sojuproject.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Long commentId;

    //@Column(nullable = false)
    private String u_nick_name;

    //@Column(nullable = false)
    //@Lob
    private String c_content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cRegDate = LocalDateTime.now();


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cModDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setCContent(String c_content) {
        this.c_content = c_content;
    }


    public void setCModDate(LocalDateTime cModDate) {
        this.cModDate = cModDate;
    }


}
