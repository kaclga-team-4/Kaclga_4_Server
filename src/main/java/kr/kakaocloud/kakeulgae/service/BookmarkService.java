package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
//    private final MemberRepository memberRepository;
//    private final JobPostingRepository jobPostingRepository;
}
