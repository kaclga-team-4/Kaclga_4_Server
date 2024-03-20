package kr.kakaocloud.kakeulgae.repository;

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
        + " join fetch jp.jobDetailPostingRelations jdpr"
        + " where jdpr.jobDetail.id in :jobDetailIds")
    List<JobPosting> findByJobDetails(@Param("jobDetailIds") Set<Long> jobDetailIds);

    @Query("select jp from JobPosting jp"
        + " join fetch jp.education e"
        + " where jp.id in :jobPostingIds")
    Slice<JobPosting> findAllByIdIn(@PathVariable("jobPostingIds")Set<Long> jobPostingIds, Pageable pageable);
}
