package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.member.CareerMemberRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerMemberRepository extends JpaRepository<CareerMemberRelation, Long> {

}
