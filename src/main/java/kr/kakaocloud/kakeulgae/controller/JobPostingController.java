package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.JobPostingService;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs")
    public Slice<JobPostingListDto> jobs(@LoginUserId Long memberId, Pageable pageable) {
        return jobPostingService.findJobPostings(memberId, pageable);
    }
}
