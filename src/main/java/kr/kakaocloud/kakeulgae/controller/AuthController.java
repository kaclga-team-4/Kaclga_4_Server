package kr.kakaocloud.kakeulgae.controller;

import jakarta.validation.Valid;
import java.util.HashMap;
import kr.kakaocloud.kakeulgae.service.AuthenticationService;
import kr.kakaocloud.kakeulgae.service.dto.auth.GoogleLoginRequest;
import kr.kakaocloud.kakeulgae.service.dto.auth.GoogleRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/google")
    public ResponseEntity<Void> googleRegister(
        @RequestBody
        @Valid
        GoogleRegisterRequest googleRegisterRequest
    ) {
        String memberName = authenticationService.getMemberNameByIdToken(
            googleRegisterRequest.getIdToken());
        googleRegisterRequest.updateMemberName(memberName);

        authenticationService.register(googleRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/test")
    public void googleRegister(
        @RequestBody
        HashMap<String, Object> body
    ) {
        System.out.println(body);
    }

    @PostMapping("/login/google")
    public ResponseEntity<Void> googleLogin(
        @RequestBody @Valid GoogleLoginRequest googleLoginRequest
    ) {
        String googleMemberName = authenticationService.getMemberNameByIdToken(
            googleLoginRequest.idToken);
        authenticationService.googleLoginProcess(googleMemberName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validate/nickname")
    public ResponseEntity<Void> validateNickname(@RequestParam("nickName") String value ){
        authenticationService.validateNickname(value);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validate/email")
    public ResponseEntity<Void> validateEmail(@RequestParam("email") String value){
        authenticationService.validateEmail(value);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validate/phone-number")
    public ResponseEntity<Void> validatePhoneNumber(@RequestParam("phoneNumber") String value) {
        authenticationService.validatePhoneNumber(value);
        return ResponseEntity.noContent().build();
    }
}
