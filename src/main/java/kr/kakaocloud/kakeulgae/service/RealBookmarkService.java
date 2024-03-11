package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.domain.dto.JobPostingDto;
import kr.kakaocloud.kakeulgae.domain.dto.RealBookmarkRequest;
import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.domain.dto.RealBookmarkResponse;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.Member;
import kr.kakaocloud.kakeulgae.domain.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.domain.repository.RealBookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class RealBookmarkService {

    private final RealBookmarkRepository bookmarkRepository;
    private final JobPostingRepository jobPostingRepository;

    public void bookmarkRegister(RealBookmarkRequest bookmarkRequest) {
        Bookmark bookmark = Bookmark.builder()
            .member(Member.builder().id(bookmarkRequest.getMemberId()).build())
            .jobPosting(JobPosting.builder().id(bookmarkRequest.getJobPostingId()).build())
            .build();
        bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(RealBookmarkRequest bookmarkRequest) {
        Bookmark bookmark = Bookmark.builder()
            .jobPosting(JobPosting.builder().id(bookmarkRequest.getJobPostingId()).build())
            .build();
        bookmarkRepository.delete(bookmark);
    }

    public ArrayList<RealBookmarkResponse> getBookmark(Long id) {

        ArrayList<Long> bookmark = bookmarkRepository.findJobPostingIdsByUserId(id);
        ArrayList<Long> bookmarkArray = new ArrayList<>();
        for (Long ele : bookmark) {
            if (!bookmarkArray.contains(ele)) {
                bookmarkArray.add(ele);
            }
        }

        ArrayList<JobPostingDto> jobPostingData = new ArrayList<>();
        for (Long ele : bookmarkArray) {
            JobPostingDto jpd = jobPostingRepository.findJobPostingNameByJobPostingId(ele);
            jobPostingData.add(jpd);
        }

        //return RealBookmarkResponse.of(bookmarkArray);
        return RealBookmarkResponse.of(jobPostingData);
    }
}
