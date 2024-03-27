package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.member.EducationMemberRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationMemberRepository extends JpaRepository<EducationMemberRelation, Long> {

}
