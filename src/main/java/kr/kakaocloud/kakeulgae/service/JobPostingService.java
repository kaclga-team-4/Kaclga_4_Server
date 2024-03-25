package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final BookmarkService bookmarkService;

    public Slice<JobPostingListDto> findPostings(Pageable pageable) {
        return jobPostingRepository.findAllWithEducation(pageable)
            .map(JobPostingListDto::new);
    }

    public Slice<JobPostingListDto> findJobPostingsByDetails(Long memberId, Pageable pageable) {
        Slice<JobPosting> jobPostings = jobPostingRepository.findAllByMemberId(memberId,
            pageable);
        Slice<JobPostingListDto> result = jobPostings.map(JobPostingListDto::new);
        for (JobPostingListDto jobPostingListDto : result) {
            bookmarkService.ifBookmarkExist(memberId, jobPostingListDto);
        }
    }
}
