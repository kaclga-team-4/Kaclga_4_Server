package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
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

    public List<Region2nd> saveUserRegion(Member member, List<Long> region2ndIds) {
        List<Region2nd> region2nds = region2ndRepository.findByIdIn(region2ndIds);
        ArrayList<RegionMemberRelation> regionMemberRelations = new ArrayList<>();

        for (Region2nd region2nd : region2nds) {
            regionMemberRelations.add(RegionMemberRelation.createRelation(member, region2nd));
        }
        region2ndMemberRelationRepository.saveAll(regionMemberRelations);

        return region2nds;
    }

    public List<Region2nd> updateUserRegion(Member member, List<Long> region2ndIds) {
        List<Region2nd> region2nds = region2ndRepository.findByIdIn(region2ndIds);
        List<RegionMemberRelation> regionMemberRelations = region2ndMemberRelationRepository.findByMember(
            member);
        region2ndMemberRelationRepository.deleteAll(regionMemberRelations);

        List<RegionMemberRelation> newRegionMemberRelations = new ArrayList<>();

        for (Region2nd region2nd : region2nds) {
            newRegionMemberRelations.add(
                RegionMemberRelation.createRelation(member, region2nd));
        }
        region2ndMemberRelationRepository.saveAll(newRegionMemberRelations);

        return region2nds;
    }
}
