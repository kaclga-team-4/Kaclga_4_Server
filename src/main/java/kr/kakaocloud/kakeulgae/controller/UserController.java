package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.UserService;
import kr.kakaocloud.kakeulgae.service.dto.member.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/info")
    public ResponseEntity<UserResponse> googleLogin(
        @LoginUserId Long userId
    ) {
        UserResponse response = userService.getInformation(userId);
        return ResponseEntity.ok(response);
    }
}
