package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import kr.kakaocloud.kakeulgae.repository.Notification.NotificationRepository;
import kr.kakaocloud.kakeulgae.service.dto.SliceResponse.SliceResponse;
import kr.kakaocloud.kakeulgae.service.dto.notification.NotificationListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public SliceResponse<NotificationListDto> getNotificationList(Long memberId, int size,
        Long lastId) {
        List<NotificationListDto> notifications = notificationRepository.findListByMemberId(
            memberId, size, lastId).stream().map(NotificationListDto::new).toList();
        return new SliceResponse(size, notifications);
    }
}
