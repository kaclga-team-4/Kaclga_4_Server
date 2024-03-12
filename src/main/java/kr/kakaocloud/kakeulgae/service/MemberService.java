package kr.kakaocloud.kakeulgae.service;

import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional(readOnly = true)
    public MemberResponse getInformation(Long userId) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
        return new MemberResponse(findMember);
    }
}
