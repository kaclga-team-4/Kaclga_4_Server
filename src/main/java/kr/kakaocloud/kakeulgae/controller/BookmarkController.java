package kr.kakaocloud.kakeulgae.controller;

import java.util.ArrayList;
import kr.kakaocloud.kakeulgae.domain.dto.BookmarkDto;
import kr.kakaocloud.kakeulgae.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

//    @GetMapping("")
//    public ArrayList<BookmarkDto> list() {
//
//    }

    @PostMapping("/{bookmark_id}")
    public void add(@PathVariable("bookmark_id") Long id) {

    }

    @DeleteMapping("/{bookmark_id}")
    public void delete(@PathVariable("bookmark_id") Long id) {

    }
}
