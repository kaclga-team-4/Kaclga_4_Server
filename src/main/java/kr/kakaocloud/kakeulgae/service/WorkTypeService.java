package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import kr.kakaocloud.kakeulgae.domain.entity.member.CareerMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.WorkTypeMemberRelation;
import kr.kakaocloud.kakeulgae.repository.JobDetailRepository;
import kr.kakaocloud.kakeulgae.repository.PreferenceJobRepository;
import kr.kakaocloud.kakeulgae.repository.WorkTypeMemberRelationRepository;
import kr.kakaocloud.kakeulgae.repository.WorkTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkTypeService {

    private final WorkTypeRepository workTypeRepository;
    private final WorkTypeMemberRelationRepository workTypeMemberRelationRepository;

    public void saveUserWorkType(Member member, List<String> workTypes) {
        List<WorkType> findWorkTypes = workTypeRepository.findByTypeIn(workTypes);
        for (WorkType findWorkType : findWorkTypes) {
            WorkTypeMemberRelation relation = WorkTypeMemberRelation.createRelation(member,
                findWorkType);
            workTypeMemberRelationRepository.save(relation);
        }
    }
}
