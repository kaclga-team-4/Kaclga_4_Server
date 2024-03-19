package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.TestImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestImageRepository extends JpaRepository<TestImage, Long> {

}
