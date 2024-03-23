package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.service.dto.BookmarkRequest;
import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.repository.BookmarkRepository;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
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

    public Slice<JobPostingListDto> getSliceBookmarkData(Long id, Pageable pageable){ // bookmark DB에 접근 -> 사용자가 찜한 공고글을 담아 Slice 객체에 삽입
        Slice<JobPostingListDto> jobPostingDtos = jobPostingRepository.findJobPostingIdsByUserIdToSlice(id, pageable)
            .map(JobPostingListDto::new);
        return jobPostingDtos;
    }

    public Slice<JobPostingListDto> getSliceSearchBookmarkData(Long id, String keyword, Pageable pageable){
        Slice<JobPostingListDto> jobPostingDtos = jobPostingRepository.findJobPostingIdsByUserIdToSlice(id, pageable)
            .map(JobPostingListDto::new);
        return jobPostingDtos;
    }

    private List<JobPostingListDto> filterSearchData(List<JobPostingListDto> jobPostingDtos, String keyword){
        List<JobPostingListDto> jobPostingDtoList = new ArrayList<>();

        return jobPostingDtos;
    }
}
