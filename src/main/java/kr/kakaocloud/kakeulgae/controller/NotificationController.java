package kr.kakaocloud.kakeulgae.controller;

import jakarta.validation.constraints.Positive;
import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.NotificationService;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("list")
    public ResponseEntity<JobPostingListDto> getNotificationList(
        @LoginUserId
        Long memberId,
        @RequestParam(name = "size", defaultValue = "10", required = false)
        @Positive(message = "size는 0보다 커야 합니다.")
        int size,
        @Positive(message = "lastId는 0보다 커야 합니다.")
        @RequestParam(name = "lastId", required = false)
        Long lastId
    ) {
        notificationService.getNotificationList(memberId, size, lastId);
        return ResponseEntity.ok().build();
    }
}
