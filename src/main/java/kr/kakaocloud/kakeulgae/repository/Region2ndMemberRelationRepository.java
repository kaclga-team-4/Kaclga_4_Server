package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.RegionMemberRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Region2ndMemberRelationRepository extends JpaRepository<RegionMemberRelation, Long> {

    List<RegionMemberRelation> findByMember(Member member);
}
