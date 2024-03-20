package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobDetailRepository extends JpaRepository<JobDetail, Long> {

    List<JobDetail> findByJobIdAndTypeIgnoreCase(Long num2, String type2);

    @Query("select jd from JobDetail jd"
        + " join fetch jd.preferenceJobs pj"
        + " join fetch pj.member m"
        + " where m.id=:memberId")
    List<JobDetail> findByMember(@Param("memberId") Long memberId);
}
