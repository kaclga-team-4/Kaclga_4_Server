package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.repository.custom.JobPostingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>,
    JobPostingRepositoryCustom {

    @Query("select jp from JobPosting jp"
        + " join fetch jp.education e")
    Slice<JobPosting> findAllWithEducation(Pageable pageable);

    @Query("select jp from JobPosting jp"
        + " join jp.jobDetailPostingRelations jdpr"
        + " join jdpr.jobDetail jd"
        + " join jd.preferenceJobs pj"
        + " join pj.member m"
        + " where m.id=:memberId")
    Slice<JobPosting> findAllByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
