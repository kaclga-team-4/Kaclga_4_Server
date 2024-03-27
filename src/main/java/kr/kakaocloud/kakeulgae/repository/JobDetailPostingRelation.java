package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDetailPostingRelation extends JpaRepository<JobDetail, Long> {

}
