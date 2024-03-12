package org.fourman.sojuproject.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.fourman.sojuproject.domain.dto.comment.*;
import org.fourman.sojuproject.domain.entity.Comment;
import org.fourman.sojuproject.reposittory.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service

public class CommentService {

    private final CommentRepository commentRepository;


    @Transactional
    public CreateCommentResponseDTO createComment(CreateCommentRequestDTO requestDTO) {

        LocalDateTime now = LocalDateTime.now();

        Comment comment = Comment.builder()
                .u_nick_name((requestDTO.getU_nick_name()))
                .c_content(requestDTO.getC_content())
                .cRegDate(requestDTO.getRegDate() != null ? requestDTO.getRegDate() : now) // 클라이언트에서 전달된 시간 또는 현재 시간 사용
                .cModDate(requestDTO.getModDate() != null ? requestDTO.getModDate() : now) // 클라이언트에서 전달된 시간 또는 현재 시간 사용
                .build();

        Comment savedComment = commentRepository.save(comment);


        return new CreateCommentResponseDTO(savedComment.getCommentId(), savedComment.getU_nick_name(),
        savedComment.getC_content(), savedComment.getCRegDate(), savedComment.getCModDate());


    }

    // 게시글 조회
    public ReadCommentResponseDTO readCommentById(Long commentId) {

        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 commentId로 조회된 댓글이 없습니다."));

        return new ReadCommentResponseDTO(foundComment.getCommentId(), foundComment.getU_nick_name(), foundComment.getC_content(), foundComment.getCRegDate(), foundComment.getCModDate());

    }

    // 게시물 수정
    @Transactional
    public UpdateCommentResponseDTO updateComment(Long commentId, UpdateCommentRequestDTO requestDTO) {

        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 commentId로 조회된 댓글이 없습니다."));

        foundComment.setCContent(requestDTO.getC_content());
        foundComment.setCModDate(requestDTO.getModDate() != null ? requestDTO.getModDate() : LocalDateTime.now());

        return new UpdateCommentResponseDTO(
                foundComment.getCommentId(),
                foundComment.getU_nick_name(),
                foundComment.getC_content(),
                foundComment.getCModDate(),
                foundComment.getCRegDate()
        );
    }

    // 게시물 삭제
    @Transactional
    public DeleteCommentResponseDTO deleteComment(Long commentId) {

        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 commentId로 조회된 게시글이 없습니다."));

        commentRepository.delete(foundComment);

        return new DeleteCommentResponseDTO(foundComment.getCommentId());

    }

    public Page<ReadCommentResponseDTO> readAllComment(Pageable pageable) {

        Page<Comment> commentPage = commentRepository.findAll(pageable);

        return commentPage.map(comment -> new ReadCommentResponseDTO(
                comment.getCommentId(),
                comment.getU_nick_name(),
                comment.getC_content(),
                comment.getCRegDate(),
                comment.getCModDate()

        ));

    }
}