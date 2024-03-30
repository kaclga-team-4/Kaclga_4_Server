package kr.kakaocloud.kakeulgae.repository.LastJobPostingId;

import kr.kakaocloud.kakeulgae.domain.entity.LastJobPostingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastJobPostingIdRepository extends JpaRepository<LastJobPostingId, Long>,
    LastJobPostingIdRepositoryCustom {

    Long findLastJobPostingIdBy();
}
