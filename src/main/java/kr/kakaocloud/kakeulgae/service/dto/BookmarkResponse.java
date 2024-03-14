package kr.kakaocloud.kakeulgae.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {

    //private Long jobPostingId;
    private String companyName;
    private String postName;
    private LocalDate deadline;

    public static ArrayList<BookmarkResponse> of(ArrayList<JobPostingDto> arr) {
        ArrayList<BookmarkResponse> bookmarkResponses = new ArrayList<>();
        for (JobPostingDto ele : arr) {
            BookmarkResponse response = BookmarkResponse.builder()
                .companyName(ele.getCompanyName())
                .postName(ele.getPostName())
                .deadline(ele.getDeadline())
                .build();
            bookmarkResponses.add(response);
        }
        return bookmarkResponses;
    }
}
