package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Region2ndRepository extends JpaRepository<Region2nd, Long> {

    List<Region2nd> findByTypeIn(List<String> types);

    List<Region2nd> findByIdIn(List<Long> region2ndIds);
}
