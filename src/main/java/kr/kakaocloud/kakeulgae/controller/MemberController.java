package kr.kakaocloud.kakeulgae.controller;

import jakarta.validation.Valid;
import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.MemberService;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberSimpleResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberUpdateNotification;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberUpdateReqeust;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestRequest;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/info")
    public ResponseEntity<MemberSimpleResponse> googleLogin(@LoginUserId Long memberId) {
        MemberSimpleResponse response = memberService.getSimpleInformation(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my")
    public ResponseEntity<MemberResponse> getMemberInfo(
        @LoginUserId Long memberId
    ) {
        return ResponseEntity.ok(memberService.getInformation(memberId));
    }


    @PatchMapping("/my")
    public ResponseEntity<Void> updateMemberInfo(
        @LoginUserId Long memberId,
        @RequestBody
        @Valid
        MemberUpdateReqeust request
    ) {
        memberService.updateInformation(memberId, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/notification")
    public ResponseEntity<Void> updateNotification(
        @LoginUserId Long memberId,
        @RequestBody
        @Valid
        MemberUpdateNotification request
    ) {
        memberService.updateNotification(memberId, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/interest/create")
    public MemberInterestResponse createInterest(@LoginUserId Long id,
        @ModelAttribute MemberInterestRequest request) {
        return memberService.createUserInterest(id, request);
    }
}
