package org.fourman.sojuproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.fourman.sojuproject.domain.dto.comment.*;
import org.fourman.sojuproject.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")

public class CommentController {

    private final CommentService commentService;



    @PostMapping
    @Operation(summary = "댓글 작성", description = "여러 필드 입력")
    public ResponseEntity<CreateCommentResponseDTO> commentCreate(@RequestBody CreateCommentRequestDTO requestDTO){

        CreateCommentResponseDTO responseDTO = commentService.createComment(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/{commentId}")
    @Operation(summary = "댓글 단일 조회", description = "commentId로 댓글 단일 조회")
    public String commentRead(@PathVariable Long commentId, Model model) {

        ReadCommentResponseDTO responseDTO= commentService.readCommentById(commentId);
        model.addAttribute("comment", responseDTO);

        return "/comment/post";
    }

    @GetMapping("/update/{commentId}")
    public String getUpdatePage(@PathVariable Long commentId, Model model) {
        ReadCommentResponseDTO responseDTO = commentService.readCommentById(commentId);
        model.addAttribute("comment", responseDTO);
        return "/comment/update";
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정", description = "commentId와 request로 댓글 수정")
    public ResponseEntity<UpdateCommentResponseDTO> commentUpdate(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDTO requestDTO) {

        UpdateCommentResponseDTO responseDTO = commentService.updateComment(commentId, requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제", description = "commentId로 게시글 삭제")
    public ResponseEntity<DeleteCommentResponseDTO> commentDelete(@PathVariable Long commentId) {

        DeleteCommentResponseDTO responseDTO = commentService.deleteComment(commentId);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping
    @Operation(summary = "댓글 전체 조회", description = "commentId로 게시글 전체 조회")
    public ResponseEntity<Page<ReadCommentResponseDTO>> commentReadAll (
            @PageableDefault(size = 5, sort = "commentId", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<ReadCommentResponseDTO> readCommentResponseDTOS = commentService.readAllComment(pageable);

        return new ResponseEntity<>(readCommentResponseDTOS, HttpStatus.OK);

    }
}
