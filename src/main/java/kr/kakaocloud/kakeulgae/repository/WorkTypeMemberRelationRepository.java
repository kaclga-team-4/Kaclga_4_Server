package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.WorkTypeMemberRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTypeMemberRelationRepository extends
    JpaRepository<WorkTypeMemberRelation, Long> {

    List<WorkTypeMemberRelation> findByMember(Member member);
}
