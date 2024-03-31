package kr.kakaocloud.kakeulgae.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kr.kakaocloud.kakeulgae.repository.JobDetailRepository;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Set<String> getPreferenceData(Long memberId, Pageable pageable) {
        Set<String> arr = new HashSet<>();
        Slice<JobPostingListDto> jobPostingListDtos = findJobPostingsByDetails(memberId, pageable);
        for(JobPostingListDto ele : jobPostingListDtos){
            for(String elel : ele.getWorkTypes()){
                arr.add(elel);
            }
            for(String elel : ele.getJobDetailTypes()){
                arr.add(elel);
            }
            for(String elel : ele.getCareers()){
                arr.add(elel);
            }
        }

        return arr;
    }
}
