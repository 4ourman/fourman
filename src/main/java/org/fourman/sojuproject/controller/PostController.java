package org.fourman.sojuproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.fourman.sojuproject.domain.dto.post.CreatePostRequestDTO;
import org.fourman.sojuproject.domain.dto.post.CreatePostResponseDTO;
import org.fourman.sojuproject.domain.dto.post.ReadPostResponseDTO;
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


}
