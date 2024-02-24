package kr.kakaocloud.kakeulgae.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

public class AuthDto {
    public class GoogleLoginRequest{
        @NotBlank
        public String idToken;
    }
public class  GoogleRegisterRequest extends RegisterRequest {
        @NotBlank
        val idToken: String,

        @Email
    override val email: String?,

        @PhoneNumber
            override val phoneNumber: String?,

        @Nickname
            override var nickname: String,

        @NotNull
            override var gender: Gender,

        @Past
            override var birthday: LocalDate,

        @NotEmpty
            override var termsIdList: Set<Long>,
        ) :  {
        @JsonIgnore
        override var username: String = ""

        @JsonIgnore
        override var socialType: SocialType = SocialType.APPLE
        fun updateUsername(username: String) {
            this.username = username
        }
    }
}

    public class RegisterRequest {
        public String email;
        public String phoneNumber = null;
        public String username;
        public String nickname;
        public Boolean gender = null;
        public LocalDate birthday;
        public SocialType socialType;
    }

    public class TokenResponse {
        String firebaseCustomToken;
    }
}
