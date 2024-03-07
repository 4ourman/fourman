package org.fourman.sojuproject.reposittory;

import org.fourman.sojuproject.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByPtitleContaining(String title, Pageable pageable);; //제목에 포함된 키워드를 찾는 메서드
}
