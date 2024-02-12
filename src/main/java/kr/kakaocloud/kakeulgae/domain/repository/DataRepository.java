package kr.kakaocloud.kakeulgae.domain.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<JobDetail, Long> {

    public void updateByIdIn(List<Long> id);
}
