package org.fourman.sojuproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fourman.sojuproject.domain.dto.post.*;
import org.fourman.sojuproject.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(jsonPath("$.u_nickname").value("테스트 닉네임"))
                .andExpect(jsonPath("$.category").value("테스트 카테고리"))
                .andExpect(jsonPath("$.p_title").value("테스트 제목")) // 수정된 부분
                .andExpect(jsonPath("$.p_content").value("테스트 내용")) // 수정된 부분
                .andDo(print());
    }

    @Test
    @DisplayName("게시물 단일 조회 기능 테스트")
    void read_post_test() throws Exception {
        // given
        Long postId = 1L;
        ReadPostResponseDTO responseDTO = new ReadPostResponseDTO(1L, "테스트 닉네임", "테스트 카테고리", "테스트 제목", "테스트 내용", LocalDateTime.now());

        given(postService.readPostById(any())).willReturn(responseDTO);

        // when & then
        mockMvc.perform(get("/api/v1/posts/{postId}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(1L))
                .andExpect(jsonPath("$.u_nickname").value("테스트 닉네임"))
                .andExpect(jsonPath("$.category").value("테스트 카테고리"))
                .andExpect(jsonPath("$.p_title").value("테스트 제목"))
                .andExpect(jsonPath("$.p_content").value("테스트 내용"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시물 수정 기능 테스트")
    void update_post_test() throws Exception {

        //given
        Long postId = 1L;
        UpdatePostRequestDTO requestDTO = new UpdatePostRequestDTO("수정 제목", "수정 내용", "수정 카테고리");
        UpdatePostResponseDTO responseDTO = new UpdatePostResponseDTO(1L, "sinbob99", "수정 카테고리", "수정 제목", "수정 내용", LocalDateTime.now());

        given(postService.updatePost(any(), any())).willReturn(responseDTO);

        // when & then
        mockMvc.perform(put("/api/v1/posts/{postId}", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(requestDTO))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(1L))
                .andExpect(jsonPath("$.u_nickname").value("sinbob99"))
                .andExpect(jsonPath("$.category").value("수정 카테고리"))
                .andExpect(jsonPath("$.p_title").value("수정 제목"))
                .andExpect(jsonPath("$.p_content").value("수정 내용"))
                .andDo(print());

    }

    @Test
    @DisplayName("게시물 삭제 기능")
    void delete_post_test() throws Exception {
        // given
        Long postId = 1L;
        DeletePostResponseDTO responseDTO = new DeletePostResponseDTO(1L);

        given(postService.deletePost(any())).willReturn(responseDTO);

        // when & then
        mockMvc.perform(delete("/api/v1/posts/{postId}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postId").value(1L))
                .andDo(print());
    }

    @Test
    @DisplayName("모든 게시물을 페이지 단위로 조회하는 기능")
    void read_all_post_test() throws Exception {
        // given
        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);
        ReadPostResponseDTO responseDTO = new ReadPostResponseDTO(1L, "테스트 닉네임", "테스트 카테고리","테스트 제목", "테스트 내용", LocalDateTime.now());
        List<ReadPostResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(responseDTO);

        Page<ReadPostResponseDTO> postResponseDTOPage = new PageImpl<>(responseDTOList, pageRequest, responseDTOList.size());

        given(postService.readAllPost(any())).willReturn(postResponseDTOPage);

        // when & then
        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].postId").value(responseDTO.getPostId()))
                .andExpect(jsonPath("$.content[0].u_nickname").value(responseDTO.getU_nickname()))
                .andExpect(jsonPath("$.content[0].category").value(responseDTO.getCategory()))
                .andExpect(jsonPath("$.content[0].p_title").value(responseDTO.getP_title()))
                .andExpect(jsonPath("$.content[0].p_content").value(responseDTO.getP_content()))
                .andDo(print());
    }

}
