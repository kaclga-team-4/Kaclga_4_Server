package kr.kakaocloud.kakeulgae.service.dto.member;

import java.time.LocalDate;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Gender;
import kr.kakaocloud.kakeulgae.domain.entity.member.MemberRole;
import kr.kakaocloud.kakeulgae.domain.entity.member.SocialType;

public class RegisterRequest {

    public String email;
    @Nullable
    public String phoneNumber;
    public String memberName;
    public String nickname;
    @Nullable
    public Gender gender;
    @Nullable
    public LocalDate birthday;
    public SocialType socialType;
    @Nullable
    public MemberRole memberRole;
}
