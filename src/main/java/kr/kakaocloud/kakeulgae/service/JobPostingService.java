package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public Slice<JobPostingListDto> findPostings(Pageable pageable) {
        return jobPostingRepository.findAllWithEducation(pageable)
            .map(JobPostingListDto::new);
    }

    public Slice<JobPostingListDto> findJobPostingsByDetails(Long memberId, Pageable pageable) {
        return jobPostingRepository.findAllByMemberId(memberId, pageable)
            .map(JobPostingListDto::new);
    }

    public Slice<JobPostingListDto> searchJobPosting(Long memberId,
        JobPostingSearchCondition condition, Pageable pageable) {
        Slice<JobPosting> results = jobPostingRepository.search(memberId, condition, pageable);
        return results.map(JobPostingListDto::new);
    }
}
