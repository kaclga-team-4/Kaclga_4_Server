package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Notification;
import org.springframework.data.domain.Pageable;

public interface NotificationRepositoryCustom {

    List<Notification> findListByMemberId(Long memberId, Pageable pageable);
}
