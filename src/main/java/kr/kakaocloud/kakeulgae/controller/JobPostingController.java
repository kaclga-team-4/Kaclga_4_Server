package kr.kakaocloud.kakeulgae.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.JobPostingService;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs/details")
    public Slice<JobPostingListDto> jobsAndJobDetails(@LoginUserId Long memberId,
        Pageable pageable) {
        return jobPostingService.findJobPostingsByDetails(memberId, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs")
    public Slice<JobPostingListDto> jobs(Pageable pageable) {
        return jobPostingService.findPostings(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs/search")
    public List<JobPostingListDto> searchJobsAndJobDetails(@LoginUserId Long memberId,
        @RequestBody JobPostingSearchCondition condition, Pageable pageable) {
        return jobPostingService.searchJobPosting(memberId, condition, pageable);
    }

    @GetMapping("jobs/preference")
    public ResponseEntity<Map<String, Set<String>>> preferenceData(@LoginUserId Long memberId,
        Pageable pageable) {
        Set<String> arr = jobPostingService.getPreferenceData(memberId, pageable);
        Map<String, Set<String>> resultMap = new HashMap<>();
        resultMap.put("preference", arr);

        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }
}
