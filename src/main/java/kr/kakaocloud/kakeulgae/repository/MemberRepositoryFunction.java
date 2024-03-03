package kr.kakaocloud.kakeulgae.repository;

import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;

public class MemberRepositoryFunction {
    private MemberRepository memberRepository;
    public Member getByMemberName(String memberName) {
        Member member = memberRepository.findByMemberName(memberName);
        if (member != null)
            return member;
        else
            throw new NoSuchElementException(STR."\{memberName}사용자가 존재하지 않습니다");
    }
    public Member getByUserId(long memberId) {
        Member member = memberRepository.findByIdOrNull(memberId);
        if (member != null) {
            return member;
        } else {
            throw new NoSuchElementException("$userId: 사용자가 존재하지 않습니다");
        }
    }

    fun UserRepository.getByUserIdWithProfile(userId: Long): User =
    findUserWithProfileById(userId) ?: throw NoSuchElementException("$userId: 사용자가 존재하지 않습니다")


}
