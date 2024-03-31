package kr.kakaocloud.kakeulgae.repository.custom;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface JobPostingRepositoryCustom {

    List<JobPosting> search(Long memberId, JobPostingSearchCondition condition, Pageable pageable);
}
