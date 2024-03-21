package kr.kakaocloud.kakeulgae.service.dto.member;

import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import lombok.Getter;

@Getter
public class MemberResponse { //회원 정보 응답 클래스(회원 정보 조회시 사용) - 테스트

    String email;
    String nickname;

    public MemberResponse(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
