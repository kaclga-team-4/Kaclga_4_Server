package kr.kakaocloud.kakeulgae.domain.repository;

import kr.kakaocloud.kakeulgae.domain.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Job, Long> {
}
