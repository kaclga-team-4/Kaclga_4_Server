package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.member.CareerMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerMemberRepository extends JpaRepository<CareerMemberRelation, Long> {

    List<CareerMemberRelation> findByMember(Member member);
}
