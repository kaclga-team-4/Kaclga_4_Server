package kr.kakaocloud.kakeulgae.service.dto.member;

import java.time.LocalDate;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Gender;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.SocialType;
import lombok.Getter;

@Getter
public class MemberResponse { //회원 정보 응답 클래스(회원 정보 조회시 사용) - 테스트
    Long memberId;
    String email;
    String nickname;
    @Nullable
    String phoneNumber;
    @Nullable
    Gender gender;
    @Nullable
    LocalDate birthday;
    SocialType socialType;

    public MemberResponse(Member member){
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
    }
}
