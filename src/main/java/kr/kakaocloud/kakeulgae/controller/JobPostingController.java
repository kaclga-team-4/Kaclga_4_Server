package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.JobPostingService;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs/details")
    public Slice<JobPostingListDto> jobsAndjobDetails(@LoginUserId Long memberId,
        Pageable pageable) {
        return jobPostingService.findJobPostingsByDetails(memberId, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs")
    public Slice<JobPostingListDto> jobs(Pageable pageable) {
        return jobPostingService.findPostings(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/jobs/search")
    public Slice<JobPostingListDto> searchJobsAndJobDetails(@LoginUserId Long memberId,
        @ModelAttribute JobPostingSearchCondition condition, Pageable pageable) {
        return jobPostingService.searchJobPosting(memberId, condition, pageable);
    }
}
