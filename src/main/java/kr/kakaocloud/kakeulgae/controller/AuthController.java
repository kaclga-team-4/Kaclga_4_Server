package kr.kakaocloud.kakeulgae.controller;

import jakarta.validation.Valid;
import kr.kakaocloud.kakeulgae.service.AuthenticationService;
import kr.kakaocloud.kakeulgae.service.dto.auth.GoogleRegisterRequest;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated // 이 어노테이션을 사용하면 @Valid 어노테이션을 사용하여 유효성 검사를 수행할 수 있음
@RestController
@RequiredArgsConstructor // final로 선언된 필드를 가지고 자동 생성자를 만들어줌
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/google")
    public ResponseEntity<MemberSimpleResponse> googleRegister(
        @RequestBody
        @Valid
        GoogleRegisterRequest googleRegisterRequest
    ) {
        MemberSimpleResponse memberSimpleResponse = authenticationService.register(
            googleRegisterRequest);
        return ResponseEntity.ok(memberSimpleResponse);//200 반환
    }

    @GetMapping("/validate/nickname")
    public ResponseEntity<Void> validateNickname(@RequestParam("nickName") String value) {
        authenticationService.validateNickname(value);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validate/email")
    public ResponseEntity<Void> validateEmail(@RequestParam("email") String value) {
        authenticationService.validateEmail(value);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validate/phone-number")
    public ResponseEntity<Void> validatePhoneNumber(@RequestParam("phoneNumber") String value) {
        authenticationService.validatePhoneNumber(value);
        return ResponseEntity.noContent().build();
    }
}
