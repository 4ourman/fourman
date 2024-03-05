package org.fourman.sojuproject.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fourman.sojuproject.domain.dto.membership.CreateMembershipRequestDTO;
import org.fourman.sojuproject.domain.dto.membership.CreateMembershipResponseDTO;
import org.fourman.sojuproject.domain.dto.membership.LoginMembershipRequestDTO;
import org.fourman.sojuproject.service.DuplicateEmailException;
import org.fourman.sojuproject.service.MembershipService;
import org.hibernate.NonUniqueResultException;
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
    public ResponseEntity<?> memberCreate(@RequestBody CreateMembershipRequestDTO request){

        log.info("😎 Controller 에서 사용되는 핸드폰번호 : {}", request.getM_phone());

        try {
            ResponseEntity<?> response = membershipService.createMember(request);
            return ResponseEntity.ok().body("회원가입이 완료되었습니다.");
        } catch (DuplicateEmailException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 사용 중인 이메일 주소입니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입에 실패했습니다.");
        }
    }



    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse response, @RequestBody LoginMembershipRequestDTO requestDTO) {
        boolean loginSuccess = membershipService.login(requestDTO.getM_email(), requestDTO.getM_password());
        if (loginSuccess) {
            // 로그인 성공 시 쿠키 설정
            Cookie cookie = new Cookie("userNickname", requestDTO.getM_email());
            cookie.setMaxAge(3600); // 쿠키의 유효 시간을 설정합니다. 여기서는 1시간으로 설정합니다.
            response.addCookie(cookie);
            return ResponseEntity.ok("로그인 성공!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패했습니다.");
        }
    }


    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // 세션에서 로그인 정보를 제거
        session.removeAttribute("isLoggedIn");
        return ResponseEntity.ok().body("로그아웃되었습니다.");
    }

    // 회원탈퇴
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(HttpSession session) {
        if (membershipService.withdraw(session)) {
            return ResponseEntity.ok().body("회원 탈퇴가 성공적으로 이루어졌습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 탈퇴에 실패하였습니다.");
        }
    }

}
