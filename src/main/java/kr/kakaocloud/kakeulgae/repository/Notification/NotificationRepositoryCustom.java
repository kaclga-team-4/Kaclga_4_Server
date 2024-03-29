package kr.kakaocloud.kakeulgae.repository.Notification;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Notification;

public interface NotificationRepositoryCustom {

    List<Notification> findListByMemberId(Long memberId, int size, Long lastId);
}
