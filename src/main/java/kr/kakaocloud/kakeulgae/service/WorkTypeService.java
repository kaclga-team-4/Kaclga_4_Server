package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.WorkTypeMemberRelation;
import kr.kakaocloud.kakeulgae.repository.WorkTypeMemberRelationRepository;
import kr.kakaocloud.kakeulgae.repository.WorkTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkTypeService {

    private final WorkTypeRepository workTypeRepository;
    private final WorkTypeMemberRelationRepository workTypeMemberRelationRepository;

    public List<WorkType> saveUserWorkType(Member member, List<Long> workTypeIds) {
        List<WorkType> workTypes = workTypeRepository.findByIdIn(workTypeIds);
        ArrayList<WorkTypeMemberRelation> workTypeMemberRelations = new ArrayList<>();

        for (WorkType workType : workTypes) {
            workTypeMemberRelations.add(WorkTypeMemberRelation.createRelation(member,
                workType));
        }
        workTypeMemberRelationRepository.saveAll(workTypeMemberRelations);

        return workTypes;
    }

    public List<WorkType> updateUserWorkType(Member member, List<Long> workTypeIds) {
        List<WorkType> workTypes = workTypeRepository.findByIdIn(workTypeIds);
        List<WorkTypeMemberRelation> workTypeMemberRelations = workTypeMemberRelationRepository.findByMember(
            member);
        workTypeMemberRelationRepository.deleteAll(workTypeMemberRelations);

        List<WorkTypeMemberRelation> newWorkTypeMemberRelations = new ArrayList<>();

        for (WorkType workType : workTypes) {
            newWorkTypeMemberRelations.add(WorkTypeMemberRelation.createRelation(member,
                workType));
        }
        workTypeMemberRelationRepository.saveAll(newWorkTypeMemberRelations);

        return workTypes;
    }
}
