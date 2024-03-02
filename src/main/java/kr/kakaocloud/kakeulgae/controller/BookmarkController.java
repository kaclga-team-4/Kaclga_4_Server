package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.domain.dto.BookmarkRequest;
import kr.kakaocloud.kakeulgae.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import kr.kakaocloud.kakeulgae.domain.dto.BookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/test/bookmark")
    public String handleBookmarkRequest(@RequestBody BookmarkRequest request) {

        Long memberId = request.getMemberId();
        Long jobPostingId = request.getJobPostingId();

        bookmarkService.postBookmark(request);

        //System.out.println("Received bookmark request for member ID: " + memberId + " and job posting ID: " + jobPostingId);

        return "Bookmark request received for member ID: " + memberId + " and job posting ID: " + jobPostingId;
    }

    @GetMapping("/bookmark/{id}")
    public ResponseEntity<ArrayList<BookmarkResponse>> getBookmarkData(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(bookmarkService.getBookmark(id));
    }
}