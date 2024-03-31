package kr.kakaocloud.kakeulgae.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.JobPostingService;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs/details")
    public Slice<JobPostingListDto> jobsAndJobDetails(@LoginUserId Long memberId, Pageable pageable) {
        return jobPostingService.findJobPostingsByDetails(memberId, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jobs")
    public Slice<JobPostingListDto> jobs(Pageable pageable) {
        return jobPostingService.findPostings(pageable);
    }

    @GetMapping("jobs/preference")
    public ResponseEntity<Map<String, Set<String>>> preferenceData(@LoginUserId Long memberId, Pageable pageable){
        Set<String> arr = jobPostingService.getPreferenceData(memberId, pageable);
        Map<String, Set<String>> resultMap = new HashMap<>();
        resultMap.put("preference", arr);

        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }

}
