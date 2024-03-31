package kr.kakaocloud.kakeulgae.repository.LastJobPostingId;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.kakaocloud.kakeulgae.domain.entity.QLastJobPostingId;
import org.springframework.stereotype.Repository;

@Repository
public class LastJobPostingIdRepositoryImpl implements LastJobPostingIdRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LastJobPostingIdRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Long findLastJobPostingIdBy() {
        return queryFactory.select(QLastJobPostingId.lastJobPostingId1.lastJobPostingId)
            .from(QLastJobPostingId.lastJobPostingId1)
            .orderBy(QLastJobPostingId.lastJobPostingId1.id.desc())
            .limit(1)
            .fetchOne();
    }
}
