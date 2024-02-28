package kr.kakaocloud.kakeulgae.controller;

import java.util.List;
import java.util.stream.Collectors;
import kr.kakaocloud.kakeulgae.domain.dto.JobPostingListDto;
import kr.kakaocloud.kakeulgae.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @GetMapping
    public Page<JobPostingListDto> list(Pageable pageable) {
        List<JobPostingListDto> collect = jobPostingService.findJobPostingsByPageRequest(pageable)
            .stream().map(JobPostingListDto::new)
            .collect(Collectors.toList());

        return new PageImpl<>(collect, pageable, collect.size());
    }
}
