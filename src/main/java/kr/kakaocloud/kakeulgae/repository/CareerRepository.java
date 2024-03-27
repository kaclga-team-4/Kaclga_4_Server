package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    public List<Career> findByTypeIn(List<String> types);

    List<Career> findByIdIn(List<Long> careerIds);
}
