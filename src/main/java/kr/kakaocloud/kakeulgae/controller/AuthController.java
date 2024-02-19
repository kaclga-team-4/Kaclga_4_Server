package kr.kakaocloud.kakeulgae.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import kr.kakaocloud.kakeulgae.domain.dto.AuthDto.GoogleLoginRequest;
import kr.kakaocloud.kakeulgae.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController{
    private final AuthenticationService authenticationService;

    @PostMapping("/login/google")
    public ResponseEntity<Void> googleLogin(
        @RequestBody @Valid GoogleLoginRequest googleLoginRequest
    ) {
        Long googleUsername = authenticationService.getUsernameByIdToken(googleLoginRequest.idToken);
        authenticationService.googleLoginProcess(googleUsername);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register/google")
    public ResponseEntity<Void> googleRegister(
        @RequestBody @Valid
        GoogleRegisterRequest googleRegisterRequest
        ) {
        authenticationService.getUsernameByIdToken(googleRegisterRequest.idToken).apply {
            googleRegisterRequest.updateUsername(this);
        }
        authenticationService.register(googleRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/validate/nickname")
    public ResponseEntity<Void> validateNickname(@RequestParam("value") String value ){
        authenticationService.validateNickname(value);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validate/email")
    public ResponseEntity<Void> validateEmail(@RequestParam("value") String value){
        authenticationService.validateEmail(value);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validate/phone-number")
    public ResponseEntity<Void> validatePhoneNumber(@RequestParam("value") String value) {
        authenticationService.validatePhoneNumber(value);
        return ResponseEntity.noContent().build();
    }
}
