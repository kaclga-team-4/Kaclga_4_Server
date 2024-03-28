package kr.kakaocloud.kakeulgae.service.dto.member;

import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import lombok.Getter;

@Getter
public class MemberSimpleResponse { //회원 정보 응답 클래스(회원 정보 조회시 사용) - 테스트

    String email;
    String nickname;
    String profileUrl;
    String phoneNumber;

    public MemberSimpleResponse(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.profileUrl = member.getProfile().getUrl();
        this.phoneNumber = member.getPhoneNumber();
    }
}
