package org.fourman.sojuproject.reposittory;

import org.fourman.sojuproject.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
