package kr.kakaocloud.kakeulgae.service.dto.auth;

import jakarta.validation.constraints.NotBlank;

    public class GoogleLoginRequest {
        @NotBlank
        public String idToken;
    }
