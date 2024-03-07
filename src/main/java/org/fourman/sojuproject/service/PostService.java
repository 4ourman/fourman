package org.fourman.sojuproject.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.fourman.sojuproject.domain.dto.post.*;
import org.fourman.sojuproject.domain.entity.Post;
import org.fourman.sojuproject.reposittory.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    // 상속
    private final PostRepository postRepository;

    // 게시글 생성
    @Transactional
    public CreatePostResponseDTO createPost(CreatePostRequestDTO requestDTO) {

        Post post = Post.builder()
                .unickname(requestDTO.getUnickname())
                .category(requestDTO.getCategory())
                .ptitle(requestDTO.getPtitle())
                .p_content(requestDTO.getP_content())
                .p_date(LocalDateTime.now())
                .build();

        Post savedPost = postRepository.save(post);

        return new CreatePostResponseDTO(savedPost.getPostId(), savedPost.getUnickname(),
                savedPost.getCategory(),  savedPost.getPtitle(), savedPost.getP_content(), savedPost.getP_date());

    }

    // 게시글 조회
    public ReadPostResponseDTO readPostById(Long postId) {

        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 postId로 조회된 게시글이 없습니다."));

        return new ReadPostResponseDTO(foundPost.getPostId(), foundPost.getUnickname(), foundPost.getCategory(), foundPost.getPtitle(), foundPost.getP_content(), foundPost.getP_date());

    }

    // 게시물 수정
    @Transactional
    public UpdatePostResponseDTO updatePost(Long postId, UpdatePostRequestDTO requestDTO) {

        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 postId로 조회된 게시글이 없습니다."));

        foundPost.update(requestDTO.getPtitle(), requestDTO.getP_content(), requestDTO.getCategory());

        return new UpdatePostResponseDTO(foundPost.getPostId(), foundPost.getUnickname(), foundPost.getCategory(), foundPost.getPtitle(), foundPost.getP_content(), foundPost.getP_date());
    }

    // 게시물 삭제
    @Transactional
    public DeletePostResponseDTO deletePost(Long postId) {

        Post foundPost = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 postId로 조회된 게시글이 없습니다."));

        postRepository.delete(foundPost);

        return new DeletePostResponseDTO(foundPost.getPostId());

    }

    public Page<ReadPostResponseDTO> readAllPost(Pageable pageable) {

        Page<Post> postPage = postRepository.findAll(pageable);

        return postPage.map(post -> new ReadPostResponseDTO(
                post.getPostId(),
                post.getUnickname(),
                post.getCategory(),
                post.getPtitle(),
                post.getP_content(),
                post.getP_date()
        ));

    }

    // searchKeword 없을때
    public Page<Post> readSearch(Pageable pageable) {

        Page<Post> postPage = postRepository.findAll(pageable);

        return postPage.map(post -> new Post(
                post.getPostId(),
                post.getUnickname(),
                post.getCategory(),
                post.getPtitle(),
                post.getP_content(),
                post.getP_date()
        ));

    }

    // searchKeyword 있을때
    public Page<Post> searchPost(String searchKeyword, Pageable pageable){ //검색 기능 추가
        return postRepository.findByPtitleContaining(searchKeyword, pageable);

    }
}
