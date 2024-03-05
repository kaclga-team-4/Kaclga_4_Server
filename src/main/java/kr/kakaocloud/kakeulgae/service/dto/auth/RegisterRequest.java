package kr.kakaocloud.kakeulgae.service.dto.auth;

import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Gender;
import kr.kakaocloud.kakeulgae.domain.entity.member.MemberRole;
import kr.kakaocloud.kakeulgae.domain.entity.member.SocialType;
import kr.kakaocloud.kakeulgae.support.validator.Nickname;
import kr.kakaocloud.kakeulgae.support.validator.PhoneNumber;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterRequest {
    @Email
    public String email;
    @Nullable
    @PhoneNumber
    public String phoneNumber;
    public String memberName;
    @Nickname
    public String nickname;
    @Nullable
    public Gender gender;
    @Nullable
    public LocalDate birthday;
    public SocialType socialType;
    @Nullable
    public MemberRole memberRole;
}
