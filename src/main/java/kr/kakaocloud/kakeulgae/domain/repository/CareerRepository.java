package kr.kakaocloud.kakeulgae.domain.repository;

import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
}
