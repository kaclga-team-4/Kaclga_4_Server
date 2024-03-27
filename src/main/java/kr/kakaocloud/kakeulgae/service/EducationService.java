package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
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

    public List<Education> saveUserEducation(Member member, List<Long> educationIds) {
        List<Education> educations = educationRepository.findByIdIn(educationIds);
        ArrayList<EducationMemberRelation> educationMemberRelations = new ArrayList<>();

        for (Education education : educations) {
            educationMemberRelations.add(EducationMemberRelation.createRelation(member,
                education));
        }
        educationMemberRepository.saveAll(educationMemberRelations);

        return educations;
    }
}
