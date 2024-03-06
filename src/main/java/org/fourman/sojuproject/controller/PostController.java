package org.fourman.sojuproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.fourman.sojuproject.domain.dto.post.*;
import org.fourman.sojuproject.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/main")
    public String mainpage(){
        return "/comment/main";
    }

    @GetMapping("/joinmember")
    public String joinpage(){
        return "/comment/joinmember";
    }

    @GetMapping("/createpost")
    public String createpost(){
        return "/comment/createpost";
    }

    @GetMapping("/post")
    public String post(){
        return "/comment/post";
    }

    @GetMapping("/memberinfo")
    public String memberinfo(){
        return "/comment/memberinfo";
    }

    @PostMapping
    @Operation(summary = "게시글 작성", description = "여러 필드 입력")
    public ResponseEntity<CreatePostResponseDTO> postCreate(@RequestBody CreatePostRequestDTO requestDTO){

        CreatePostResponseDTO responseDTO = postService.createPost(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/{postId}")
    @Operation(summary = "게시글 단일 조회", description = "postId로 게시글 단일 조회")
    public ResponseEntity<ReadPostResponseDTO> postRead(@PathVariable Long postId) {

        ReadPostResponseDTO responseDTO = postService.readPostById(postId);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/{postId}")
    @Operation(summary = "게시글 수정", description = "postId와 request로 게시글 수정")
    public ResponseEntity<UpdatePostResponseDTO> postUpdate(@PathVariable Long postId, @RequestBody UpdatePostRequestDTO requestDTO) {

        UpdatePostResponseDTO responseDTO = postService.updatePost(postId, requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "게시글 삭제", description = "postId로 게시글 삭제")
    public ResponseEntity<DeletePostResponseDTO> postDelete(@PathVariable Long postId) {

        DeletePostResponseDTO responseDTO = postService.deletePost(postId);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "postId로 게시글 전체 조회")
    public ResponseEntity<Page<ReadPostResponseDTO>> postReadAll (
            @PageableDefault(size = 5, sort = "postId", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<ReadPostResponseDTO> readPostResponseDTOS = postService.readAllPost(pageable);

        return new ResponseEntity<>(readPostResponseDTOS, HttpStatus.OK);

    }
}
