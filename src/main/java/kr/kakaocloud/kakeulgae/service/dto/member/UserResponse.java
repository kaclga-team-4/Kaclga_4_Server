package kr.kakaocloud.kakeulgae.service.dto.member;

import java.time.LocalDate;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Gender;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.SocialType;
import lombok.Getter;

@Getter
public class UserResponse{
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

    public UserResponse(Member member){
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
    }
}
