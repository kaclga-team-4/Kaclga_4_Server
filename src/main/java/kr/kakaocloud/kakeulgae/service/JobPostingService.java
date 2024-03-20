package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.repository.JobDetailRepository;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobDetailRepository jobDetailRepository;
    private final JobPostingRepository jobPostingRepository;

    public Slice<JobPostingListDto> findJobPostingsByDetails(Long memberId, Pageable pageable) {
        List<JobDetail> jobDetails = jobDetailRepository.findByMember(memberId);
        Set<Long> jobDetailIds = jobDetails.stream().map(jd -> jd.getId())
            .collect(Collectors.toSet());

        for (Long jobDetailId : jobDetailIds) {
            System.out.println("jobDetailId = " + jobDetailId);
        }

        List<JobPosting> jobPostings = jobPostingRepository.findByJobDetails(jobDetailIds);
        Set<Long> jobPostingIds = jobPostings.stream().map(jp -> jp.getId())
            .collect(Collectors.toSet());

        for (Long jobPostingId : jobPostingIds) {
            System.out.println("jobPostingId = " + jobPostingId);
        }

        return jobPostingRepository.findAllByIdIn(jobPostingIds, pageable)
            .map(JobPostingListDto::new);
    }

    public Slice<JobPostingListDto> findPostings(Pageable pageable) {
        return jobPostingRepository.findAllWithEducation(pageable)
            .map(JobPostingListDto::new);
    }
}
