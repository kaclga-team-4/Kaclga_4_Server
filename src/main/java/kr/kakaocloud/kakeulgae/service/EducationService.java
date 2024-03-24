package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.member.EducationMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.EducationMemberRepository;
import kr.kakaocloud.kakeulgae.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationMemberRepository educationMemberRepository;

    public void saveUserEducation(Member member, List<String> educations) {
        List<Education> findEducations = educationRepository.findByTypeIn(educations);
        for (Education findEducation : findEducations) {
            EducationMemberRelation relation = EducationMemberRelation.createRelation(member,
                findEducation);
            educationMemberRepository.save(relation);
        }
    }
}
