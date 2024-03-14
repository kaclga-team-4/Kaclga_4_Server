package kr.kakaocloud.kakeulgae.controller;

import java.util.ArrayList;
import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.MemberService;
import kr.kakaocloud.kakeulgae.service.dto.RealBookmarkRequest;
import kr.kakaocloud.kakeulgae.service.dto.RealBookmarkResponse;
import kr.kakaocloud.kakeulgae.service.BookmarkService;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> handleBookmarkRequest(@RequestBody RealBookmarkRequest request) { // 즐겨찾기 등록 API
        try{
            bookmarkService.bookmarkRegister(request);
            return ResponseEntity.status(HttpStatus.OK).body("Register Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 공고입니다");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> handleBookmarkDelete(@PathVariable(value = "id") Long id) { // 즐겨찾기 해제 API
        try{
            bookmarkService.deleteBookmark(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 공고입니다");
        }
    }

    @GetMapping("/{id}") // 즐겨찾기 조회 API -> id를 통해 사용자 식별
    public ResponseEntity<ArrayList<RealBookmarkResponse>> getBookmarkData(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(bookmarkService.getBookmark(id));
    }

    @GetMapping("/mybookmark") // 즐겨찾기 조회 API -> 토큰을 통해 사용자 식별
    public ResponseEntity<ArrayList<RealBookmarkResponse>> getMyBookmark(@LoginUserId Long id){
        MemberResponse response = memberService.getInformation(id);
        return ResponseEntity.ok(bookmarkService.getBookmark(response.getMemberId()));
    }
}
