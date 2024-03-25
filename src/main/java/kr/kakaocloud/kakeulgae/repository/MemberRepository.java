package kr.kakaocloud.kakeulgae.repository;

import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNickname(String nickname);

    boolean existsAllByMemberName(String memberName);

    @Nullable
    Member findByMemberName(String memberName);

}
