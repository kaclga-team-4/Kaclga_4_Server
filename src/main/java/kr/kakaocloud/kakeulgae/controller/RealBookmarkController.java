package kr.kakaocloud.kakeulgae.controller;

import java.util.ArrayList;
import kr.kakaocloud.kakeulgae.domain.dto.RealBookmarkRequest;
import kr.kakaocloud.kakeulgae.domain.dto.RealBookmarkResponse;
import kr.kakaocloud.kakeulgae.service.RealBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RealBookmarkController {

    private final RealBookmarkService bookmarkService;

    @PostMapping("/bookmarks/register")
    public String handleBookmarkRequest(@RequestBody RealBookmarkRequest request) { // 즐겨찾기 등록 기능

        Long memberId = request.getMemberId();
        Long jobPostingId = request.getJobPostingId();

        bookmarkService.bookmarkRegister(request);

        return "Bookmark request received for member ID: " + memberId + " and job posting ID: "
            + jobPostingId;
    }

    @DeleteMapping("/bookmarks/delete")
    public String handleBookarkDelete(@RequestBody RealBookmarkRequest request) {

        Long memberId = request.getMemberId();
        Long jobPostingId = request.getJobPostingId();

        bookmarkService.deleteBookmark(request);

        return "Delete Success";
    }

    @GetMapping("/bookmark/{id}")
    public ResponseEntity<ArrayList<RealBookmarkResponse>> getBookmarkData(
        @PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(bookmarkService.getBookmark(id));
    }
}
