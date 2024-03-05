package kr.kakaocloud.kakeulgae.service.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Gender;
import kr.kakaocloud.kakeulgae.domain.entity.member.MemberRole;
import kr.kakaocloud.kakeulgae.domain.entity.member.SocialType;
import lombok.Getter;

@Getter
public class GoogleRegisterRequest extends RegisterRequest {

    @NotEmpty
    String idToken;

    public GoogleRegisterRequest(@Email String email, @Nullable String phoneNumber, String memberName,
        String nickname, @Nullable Gender gender, @Nullable LocalDate birthday,
        @Nullable MemberRole memberRole,
        @NotEmpty String idToken) {
        super(email, phoneNumber, memberName, nickname, gender, birthday, SocialType.GOOGLE, memberRole == null ? MemberRole.ROLE_USER : memberRole
);
        this.idToken = idToken;
    }

    public void updateMemberName(String memberName) {
        this.memberName = memberName;
    }
}
