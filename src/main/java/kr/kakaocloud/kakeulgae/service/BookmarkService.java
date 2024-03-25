package kr.kakaocloud.kakeulgae.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.BookmarkRepository;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;

    public boolean ifBookmarkExist(Long memberId, JobPosting jobPosting) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() ->
            new NoSuchElementException("회원이 존재하지 않습니다"));
        Optional<Bookmark> result = bookmarkRepository.findByMemberAndJobPosting(
            findMember, jobPosting);
        return result.isPresent();
    }
}
