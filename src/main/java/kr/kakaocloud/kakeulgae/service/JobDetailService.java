package kr.kakaocloud.kakeulgae.service;

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

    public void saveUserJobDetail(Member member, List<String> jobDetails) {
        List<JobDetail> findJobDetails = jobDetailRepository.findByTypeIn(jobDetails);

        for (JobDetail findJobDetail : findJobDetails) {
            PreferenceJob relation = PreferenceJob.createRelation(member, findJobDetail);
            preferenceJobRepository.save(relation);
        }
    }
}
