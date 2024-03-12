package kr.kakaocloud.kakeulgae.service.dto.auth;

import jakarta.validation.constraints.NotBlank;

    public class GoogleLoginRequest { //구글 로그인 요청을 담는 클래스
        @NotBlank
        public String idToken;
    }
