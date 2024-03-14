package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.service.dto.JobPostingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    @Query("SELECT new kr.kakaocloud.kakeulgae.service.dto.JobPostingDto(b.companyName, b.postName, b.deadline) FROM JobPosting b WHERE b.id = :jobPostingId")
    JobPostingDto findJobPostingNameByJobPostingId(@Param("jobPostingId") Long jobPostingId);
}
