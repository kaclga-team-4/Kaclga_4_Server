package kr.kakaocloud.kakeulgae.repository.Notification;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Notification;
import kr.kakaocloud.kakeulgae.domain.entity.QNotification;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NotificationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Notification> findListByMemberId(Long memberId, int size, Long lastId) {
        QNotification notification = QNotification.notification;
        return queryFactory.selectFrom(notification)
            .where(notification.member.id.eq(memberId), dynamicPredicateByLastId(lastId))
            .orderBy(notification.id.desc())
            .limit(size + 1)
            .fetch();
    }

    private BooleanExpression dynamicPredicateByLastId(Long lastId) {
        return lastId != 0 ? QNotification.notification.id.lt(lastId) : null;
    }
}
