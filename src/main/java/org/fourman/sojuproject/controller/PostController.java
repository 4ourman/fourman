package org.fourman.sojuproject.controller;


import lombok.extern.log4j.Log4j2;
import org.fourman.sojuproject.domain.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import java.util.List;

@Controller
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {

    private final PostService postService;

//    @GetMapping("/main")
//    public String main() {
//        return "/comment/main";
//    }

    @GetMapping("/main")
    public String main(Model model, @PageableDefault(page = 0, size = 10, sort = "postId",
            direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword) { //검색 키워드를 searchKeyword로 전달 받음

        Page<Post> readposts;

        if(searchKeyword == null) { //검색할 키워드가 들어오지 않은 경우 전체 리스트 출력
            // 제목과 작성자가 바뀌어서 출력되는 상태
            readposts = postService.readSearch(pageable);
        } else {
            //검색할 키워드가 들어온 경우 검색 기능이 포함된 리스트 반환
            // 제목과 작성자가 정상적으로 출력되는 상태
            log.info("✌ searchKeyword : {}", searchKeyword);
            readposts = postService.searchPost(searchKeyword, pageable);
        }

        int nowPage = readposts.getPageable().getPageNumber() + 1; //pageable이 가지고 있는 페이지는 0부터 시작이라 1을 더함

        int startPage = Math.max(nowPage -4, 1); //1보다 작은 경우는 1을 반환

        int endPage = Math.min(nowPage + 5, readposts.getTotalPages()); //전체 페이지보다 많은 경우는 전체 페이지를 반환

        model.addAttribute("readposts", readposts);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage",endPage);
        return "comment/main";
    }

    @GetMapping("/joinmember")
    public String joinpage(){
        return "/comment/joinmember";
    }

    @GetMapping("/createpost")
    public String createpost(){
        return "/comment/createpost";
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

    @GetMapping("/post/{postId}")
    @Operation(summary = "게시글 단일 조회", description = "postId로 게시글 단일 조회")
    public String postRead(@PathVariable Long postId, Model model) {
        ReadPostResponseDTO responseDTO = postService.readPostById(postId);
        model.addAttribute("post", responseDTO);
        return "/comment/post";
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
