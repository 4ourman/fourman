package org.fourman.sojuproject.reposittory;

import org.fourman.sojuproject.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
