package kr.kakaocloud.kakeulgae.repository.custom;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

public interface JobPostingRepositoryCustom {

    List<JobPosting> search(Long memberId, JobPostingSearchCondition condition, Pageable pageable);
    Slice<JobPosting> findBySearchBookmarkData(Long id, @Param("keyword") String keyword, Pageable pageable);

    Slice<JobPosting> findBySearchJobData(@Param("keyword") String keyword, Pageable pageable);
}
