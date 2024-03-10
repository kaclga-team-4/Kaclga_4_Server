package kr.kakaocloud.kakeulgae.service.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import kr.kakaocloud.kakeulgae.domain.entity.member.Gender;
import kr.kakaocloud.kakeulgae.domain.entity.member.MemberRole;
import kr.kakaocloud.kakeulgae.domain.entity.member.SocialType;
import kr.kakaocloud.kakeulgae.support.validator.Nickname;
import kr.kakaocloud.kakeulgae.support.validator.PhoneNumber;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterRequest { //공통 회원가입 요청 클래스
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
    @JsonIgnore
    public SocialType socialType;
    @Nullable
    public MemberRole memberRole;
}
