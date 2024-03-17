package kr.kakaocloud.kakeulgae.service;

import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.service.dto.BookmarkRequest;
import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.repository.BookmarkRepository;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.service.dto.JobPostingDto;
import kr.kakaocloud.kakeulgae.service.dto.SliceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final JobPostingRepository jobPostingRepository;
    private final MemberRepository memberRepository;

    public void bookmarkRegister(BookmarkRequest bookmarkRequest) {
        Bookmark bookmark = Bookmark.builder()
            .member(memberRepository.findById(bookmarkRequest.getMemberId()).orElseThrow(() ->
                new NoSuchElementException("해당 유저가 존재하지 않습니다")))
            .jobPosting(jobPostingRepository.findById(bookmarkRequest.getJobPostingId()).orElseThrow(() ->
                new NoSuchElementException("해당 공고글이 존재하지 않습니다")))
            .build();
        bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(Long id) {
        bookmarkRepository.delete(bookmarkRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("해당 유저가 존재하지 않습니다")));
    }

    public SliceResponse getSliceBookmarkData(Long id, Pageable pageable){
        Slice<JobPostingDto> slice = jobPostingRepository.findJobPostingIdsByUserIdToSlice(id, pageable); // bookmark DB에 접근 -> 사용자가 찜한 공고글을 담아 Slice 객체에 삽입
        return new SliceResponse(slice);
    }

    public SliceResponse getSliceSearchBookmarkData(Long id, String keyword, Pageable pageable){
        Slice<JobPostingDto> slice = jobPostingRepository.findSearchJobPostingIdsByUserIdToSlice(id, keyword, pageable);
        return new SliceResponse(slice);
    }
}
