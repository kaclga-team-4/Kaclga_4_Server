package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.PreferenceJob;
import kr.kakaocloud.kakeulgae.domain.entity.member.RegionMemberRelation;
import kr.kakaocloud.kakeulgae.repository.Region2ndMemberRelationRepository;
import kr.kakaocloud.kakeulgae.repository.Region2ndRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final Region2ndRepository region2ndRepository;
    private final Region2ndMemberRelationRepository region2ndMemberRelationRepository;

    public void saveUserRegion(Member member, List<String> region2nds) {
        List<Region2nd> findRegion2nds = region2ndRepository.findByTypeIn(region2nds);
        for (Region2nd findRegion2nd : findRegion2nds) {
            RegionMemberRelation relation = RegionMemberRelation.createRelation(member, findRegion2nd);
            region2ndMemberRelationRepository.save(relation);
        }
    }
}
