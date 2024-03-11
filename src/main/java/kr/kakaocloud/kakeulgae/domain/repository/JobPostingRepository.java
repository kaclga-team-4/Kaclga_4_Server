package kr.kakaocloud.kakeulgae.domain.repository;

import kr.kakaocloud.kakeulgae.domain.dto.JobPostingDto;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    @Query("SELECT new kr.kakaocloud.kakeulgae.domain.dto.JobPostingDto(b.companyName, b.postName, b.deadline) FROM JobPosting b WHERE b.id = :jobPostingId")
    JobPostingDto findJobPostingNameByJobPostingId(@Param("jobPostingId") Long jobPostingId);
}
