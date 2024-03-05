package org.fourman.sojuproject.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fourman.sojuproject.domain.dto.membership.CreateMembershipRequestDTO;
import org.fourman.sojuproject.domain.dto.membership.CreateMembershipResponseDTO;
import org.fourman.sojuproject.domain.entity.Membership;
import org.fourman.sojuproject.reposittory.LoginMembershipRepository;
import org.fourman.sojuproject.reposittory.MembershipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final LoginMembershipRepository loginMembershipRepository;

    // 회원가입
    @Transactional
    public CreateMembershipResponseDTO createMember(CreateMembershipRequestDTO request) {

        log.info("😎 DTO로 받아 Service에서 사용되는 핸드폰번호 : {}", request.getM_phone());
        Membership member = Membership.builder()
                .mName(request.getM_name())
                .mEmail(request.getM_email())
                .mPassword(request.getM_password())
                .mPhone(request.getM_phone())
                .mAddress(request.getM_address())
                .mNickName(request.getM_nick_name())
                .build();

        Membership savedMember = membershipRepository.save(member);

        return new CreateMembershipResponseDTO(savedMember.getMemberId(), savedMember.getMName(), savedMember.getMEmail(), savedMember.getMPassword(), savedMember.getMPhone(), savedMember.getMAddress(), savedMember.getMNickName());
    }

    // 로그인
    @Transactional(readOnly = true)
    public boolean login(String email, String password) {
        Membership member = loginMembershipRepository.findBymEmail(email);

        if (member != null && member.getMPassword().equals(password)) {
            // 로그인 성공
            return true;
        } else {
            // 로그인 실패
            return false;
        }
    }

    //로그아웃
    public void logout(HttpSession session) {
        session.removeAttribute("loggedInMember");
    }

    // 회원탈퇴
    @Transactional
    public boolean withdraw(HttpSession session) {
        Membership loggedInMember = (Membership) session.getAttribute("loggedInMember");

        if (loggedInMember != null) {
            try {
                membershipRepository.delete(loggedInMember);
                session.invalidate();
                return true;
            } catch (Exception e) {
                log.error("회원 탈퇴에 실패하였습니다.", e);
                return false;
            }
        } else {
            log.error("로그인한 사용자 정보를 찾을 수 없습니다.");
            return false;
        }
    }

}
