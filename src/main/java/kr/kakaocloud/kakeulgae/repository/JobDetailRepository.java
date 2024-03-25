package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import java.util.Set;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobDetailRepository extends JpaRepository<JobDetail, Long> {

    List<JobDetail> findByJobIdAndTypeIgnoreCase(Long num2, String type2);

    List<JobDetail> findByTypeIn(List<String> jobDetails);

    List<JobDetail> findByIdIn(List<Long> jobDetailIds);

    @Query("select jd.id from JobDetail jd"
        + " join jd.preferenceJobs pj"
        + " join pj.member m"
        + " where m.id=:memberId")
    Set<Long> findByMember(@Param("memberId") Long memberId);
}
