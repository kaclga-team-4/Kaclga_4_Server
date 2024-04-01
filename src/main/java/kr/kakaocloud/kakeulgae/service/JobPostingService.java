package kr.kakaocloud.kakeulgae.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.service.dto.bookmark.BookmarkListDto;
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

    public List<JobPostingListDto> searchJobPosting(Long memberId,
        JobPostingSearchCondition condition, Pageable pageable) {
        List<JobPosting> results = jobPostingRepository.search(memberId, condition, pageable);
        return results.stream().map(JobPostingListDto::new).toList();
    }

    public Set<String> getPreferenceData(Long memberId, Pageable pageable) {
        Set<String> arr = new HashSet<>();
        Slice<JobPostingListDto> jobPostingListDtos = findJobPostingsByDetails(memberId, pageable);
        for (JobPostingListDto ele : jobPostingListDtos) {
            for (String elel : ele.getWorkTypes()) {
                arr.add(elel);
            }
            for (String elel : ele.getJobDetailTypes()) {
                arr.add(elel);
            }
            for (String elel : ele.getCareers()) {
                arr.add(elel);
            }
        }

        return arr;
    }

    public Slice<BookmarkListDto> getSliceSearchJobData(String keyword, Pageable pageable){
        Slice<BookmarkListDto> bookmarkListDtosToJobData = jobPostingRepository.findBySearchJobData(keyword, pageable).map(BookmarkListDto::new);
        return bookmarkListDtosToJobData;
    }
}
