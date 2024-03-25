package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {

    public List<WorkType> findByTypeIn(List<String> types);

    List<WorkType> findByIdIn(List<Long> workTypeIds);
}
