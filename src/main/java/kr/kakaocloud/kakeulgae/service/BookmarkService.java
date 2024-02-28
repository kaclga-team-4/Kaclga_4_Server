package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.domain.dto.BookmarkRequest;
import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.domain.entity.TestEntity;
import kr.kakaocloud.kakeulgae.domain.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public void postBookmark(BookmarkRequest bookmarkRequest){
        Bookmark bookmark = Bookmark.builder()
                                .memberId(bookmarkRequest.getMemberId())
                                .jobPostingId(bookmarkRequest.getJobPostingId())
                                .build();
        bookmarkRepository.save(bookmark);
    }
}
