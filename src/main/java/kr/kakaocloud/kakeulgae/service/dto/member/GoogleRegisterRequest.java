package kr.kakaocloud.kakeulgae.service.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Gender;
import kr.kakaocloud.kakeulgae.domain.entity.member.SocialType;
import kr.kakaocloud.kakeulgae.support.validator.Nickname;
import kr.kakaocloud.kakeulgae.support.validator.PhoneNumber;

public class GoogleRegisterRequest extends RegisterRequest {

    @NotBlank
    String idToken;
    @Email
    String email;

    @PhoneNumber
    @Nullable
    String phoneNumber;

    @Nickname
    String nickname;

    @NotNull
    Gender gender;

    @Past
    LocalDate birthday;

    @JsonIgnore
    String memberName = "";

    @JsonIgnore
    SocialType socialType = SocialType.GOOGLE;

    public void updateMemberName(String memberName) {
        this.memberName = memberName;
    }
}
