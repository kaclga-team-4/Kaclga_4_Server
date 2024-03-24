package kr.kakaocloud.kakeulgae.repository;

import io.micrometer.observation.ObservationFilter;
import java.util.List;
import java.util.Set;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    @Query("select jp from JobPosting jp"
        + " join fetch jp.education e")
    Slice<JobPosting> findAllWithEducation(Pageable pageable);

    @Query("select jp from JobPosting jp"
        + " join jp.jobDetailPostingRelations jdpr"
        + " join jdpr.jobDetail jd"
        + " join jd.preferenceJobs pj"
        + " join pj.member m"
        + " where m.id=:memberId")
    Slice<JobPosting> findAllByMemberId(@Param("memberId")Long memberId, Pageable pageable);
}
