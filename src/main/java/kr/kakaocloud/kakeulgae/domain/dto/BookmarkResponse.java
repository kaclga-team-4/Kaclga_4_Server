package kr.kakaocloud.kakeulgae.domain.dto;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {
    private Long jobPostingId;
    public static ArrayList<BookmarkResponse> of(ArrayList<Long> arr) {
        ArrayList<BookmarkResponse> bookmarkArray = new ArrayList<>();
        for(Long ele : arr){
            if(!bookmarkArray.contains(BookmarkResponse.builder().jobPostingId(ele).build())){
                bookmarkArray.add(BookmarkResponse.builder().jobPostingId(ele).build());
            }
        }
        return bookmarkArray;
    }
}
