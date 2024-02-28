package kr.kakaocloud.kakeulgae.domain.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    @Query("select jp from JobPosting jp"
        + " join fetch jp.job j"
        + " join fetch jp.education e"
        + " join fetch jp.career c"
        + " order by jp.createdAt desc")
    List<JobPosting> findAllJobPosting(Pageable pageable);
}
