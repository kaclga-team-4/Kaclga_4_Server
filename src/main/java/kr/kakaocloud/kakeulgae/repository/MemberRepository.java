package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNickname(String nickname);

    boolean existsAllByMemberName(String memberName);

}
