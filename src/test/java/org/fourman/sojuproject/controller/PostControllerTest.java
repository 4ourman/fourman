package org.fourman.sojuproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fourman.sojuproject.domain.dto.post.CreatePostRequestDTO;
import org.fourman.sojuproject.domain.dto.post.CreatePostResponseDTO;
import org.fourman.sojuproject.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("게시물 작성 기능 테스트")
    void create_post_test() throws Exception {
        //given
        CreatePostRequestDTO requestDTO = new CreatePostRequestDTO("테스트 닉네임", "테스트 카테고리", "테스트 제목", "테스트 내용");
        CreatePostResponseDTO responseDTO = new CreatePostResponseDTO(1L, "테스트 닉네임", "테스트 카테고리", "테스트 제목", "테스트 내용", LocalDateTime.now());

        given(postService.createPost(any())).willReturn(responseDTO);

        //when & then
        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestDTO))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(1L))
                .andExpect(jsonPath("$.unickname").value("테스트 닉네임"))
                .andExpect(jsonPath("$.category").value("테스트 카테고리"))
                .andExpect(jsonPath("$.ptitle").value("테스트 제목")) // 수정된 부분
                .andExpect(jsonPath("$.pcontent").value("테스트 내용")) // 수정된 부분
                .andDo(print());
    }

}
