package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.member.PreferenceJob;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.JobDetailRepository;
import kr.kakaocloud.kakeulgae.repository.PreferenceJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobDetailService {

    private final JobDetailRepository jobDetailRepository;
    private final PreferenceJobRepository preferenceJobRepository;

    public List<JobDetail> saveUserJobDetail(Member member, List<Long> jobDetailIds) {
        List<JobDetail> jobDetails = jobDetailRepository.findByIdIn(jobDetailIds);
        ArrayList<PreferenceJob> preferenceJobs = new ArrayList<>();

        for (JobDetail jobDetail : jobDetails) {
            preferenceJobs.add(PreferenceJob.createRelation(member, jobDetail));
        }
        preferenceJobRepository.saveAll(preferenceJobs);

        return jobDetails;
    }
}
