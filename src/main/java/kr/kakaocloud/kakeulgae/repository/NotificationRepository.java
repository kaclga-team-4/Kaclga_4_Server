package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface NotificationRepository extends JpaRepository<Notification, Long> {
    public Notification findListByMemberId(Long memberId, Pageable pageable){
        int pageSize = pageable.getPageSize();
        List<Notification> content =
    }
}
