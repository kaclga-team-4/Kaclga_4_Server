package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.member.EducationMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationMemberRepository extends JpaRepository<EducationMemberRelation, Long> {

    List<EducationMemberRelation> findByMember(Member member);
}
