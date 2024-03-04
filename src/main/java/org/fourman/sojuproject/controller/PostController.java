package org.fourman.sojuproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.fourman.sojuproject.domain.dto.post.*;
import org.fourman.sojuproject.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<CreatePostResponseDTO> postCreate(@RequestBody CreatePostRequestDTO requestDTO){

        CreatePostResponseDTO responseDTO = postService.createPost(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<ReadPostResponseDTO> postRead(@PathVariable Long postId) {

        ReadPostResponseDTO responseDTO = postService.readPostById(postId);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @PutMapping("/{postId}")
    public ResponseEntity<UpdatePostResponseDTO> postUpdate(@PathVariable Long postId, @RequestBody UpdatePostRequestDTO requestDTO) {

        UpdatePostResponseDTO responseDTO = postService.updatePost(postId, requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
