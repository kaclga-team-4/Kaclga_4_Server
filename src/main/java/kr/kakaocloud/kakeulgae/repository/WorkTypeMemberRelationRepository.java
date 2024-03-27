package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.member.WorkTypeMemberRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTypeMemberRelationRepository extends
    JpaRepository<WorkTypeMemberRelation, Long> {

}
