package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.MemberService;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/info")
    public ResponseEntity<MemberResponse> googleLogin(@LoginUserId Long memberId) {
        MemberResponse response = memberService.getInformation(memberId);
        return ResponseEntity.ok(response);
    }
}
