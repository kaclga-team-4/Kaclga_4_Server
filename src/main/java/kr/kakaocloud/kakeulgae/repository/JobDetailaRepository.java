package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDetailaRepository extends JpaRepository<JobDetail, Long> {

    List<JobDetail> findByJobIdAndTypeIgnoreCase(Long num2, String type2);

}
