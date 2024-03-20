package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.MemberService;
import kr.kakaocloud.kakeulgae.service.dto.BookmarkRequest;
import kr.kakaocloud.kakeulgae.service.BookmarkService;
import kr.kakaocloud.kakeulgae.service.dto.JobPostingDto;
import kr.kakaocloud.kakeulgae.service.dto.SliceResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    public ResponseEntity<String> handleBookmarkRequest(@RequestBody BookmarkRequest request) { // 즐겨찾기 등록 API
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

    //@GetMapping("/likes") // 즐겨찾기 조회 API -> 토큰을 통해 사용자 식별하고 페이지네이션을 활용하여 조회
    //public ResponseEntity<SliceResponse> getMyBookmark(@LoginUserId Long id, Pageable pageable){
   //     MemberResponse response = memberService.getInformation(id);
 //       return ResponseEntity.status(HttpStatus.OK).body(bookmarkService.getSliceBookmarkData(response.getMemberId(), pageable));
//    }

    @GetMapping("/likes") // 즐겨찾기 조회 API -> 토큰을 통해 사용자 식별하고 페이지네이션을 활용하여 조회
    public Slice<JobPostingDto> getMyBookmark(@LoginUserId Long id, Pageable pageable){
        return bookmarkService.getSliceBookmarkData(id, pageable);
    }

    @GetMapping("/search")
    public ResponseEntity<SliceResponse> getSearchBookmark(@LoginUserId Long id, @RequestParam(value = "keyword") String keyword, Pageable pageable){
        System.out.print("keyword : ");
        System.out.println(keyword);
        MemberResponse response = memberService.getInformation(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookmarkService.getSliceSearchBookmarkData(response.getMemberId(), keyword, pageable));
    }
}
