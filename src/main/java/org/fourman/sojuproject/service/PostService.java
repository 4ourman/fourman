package org.fourman.sojuproject.service;

import lombok.RequiredArgsConstructor;
import org.fourman.sojuproject.domain.dto.post.CreatePostRequestDTO;
import org.fourman.sojuproject.domain.dto.post.CreatePostResponseDTO;
import org.fourman.sojuproject.domain.dto.post.ReadPostResponseDTO;
import org.fourman.sojuproject.domain.entity.Post;
import org.fourman.sojuproject.reposittory.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public CreatePostResponseDTO createPost(CreatePostRequestDTO requestDTO) {

        Post post = Post.builder()
                .u_nickname(requestDTO.getU_nickname())
                .category(requestDTO.getCategory())
                .p_title(requestDTO.getP_title())
                .p_content(requestDTO.getP_content())
                .p_date(LocalDateTime.now())
                .build();

        Post savedPost = postRepository.save(post);

        return new CreatePostResponseDTO(savedPost.getPostId(), savedPost.getU_nickname(),
                 savedPost.getCategory(),  savedPost.getP_title(), savedPost.getP_content(), savedPost.getP_date());

    }

}
