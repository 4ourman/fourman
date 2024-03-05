package org.fourman.sojuproject.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fourman.sojuproject.domain.dto.membership.CreateMembershipRequestDTO;
import org.fourman.sojuproject.domain.dto.membership.CreateMembershipResponseDTO;
import org.fourman.sojuproject.domain.dto.membership.LoginMembershipRequestDTO;
import org.fourman.sojuproject.service.MembershipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<CreateMembershipResponseDTO> memberCreate(@RequestBody CreateMembershipRequestDTO request){

        log.info("😎 Controller 에서 사용되는 핸드폰번호 : {}", request.getM_phone());

        CreateMembershipResponseDTO response = membershipService.createMember(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // id 중복체크


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginMembershipRequestDTO requestDTO, HttpSession session) {
        boolean loginSuccess = membershipService.login(requestDTO.getM_email(), requestDTO.getM_password());

        if (loginSuccess) {
            session.setAttribute("isLoggedIn", true);
            return ResponseEntity.ok().body("로그인에 성공하였습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패하였습니다.");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // 세션에서 로그인 정보를 제거
        session.removeAttribute("isLoggedIn");
        return ResponseEntity.ok().body("로그아웃되었습니다.");
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(HttpSession session) {
        if (membershipService.withdraw(session)) {
            return ResponseEntity.ok().body("회원 탈퇴가 성공적으로 이루어졌습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴에 실패하였습니다.");
        }
    }


}
