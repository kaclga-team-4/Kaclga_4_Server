package kr.kakaocloud.kakeulgae.service.dto;

import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
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
public class RealBookmarkResponse {

    //private Long jobPostingId;
    private String companyName;
    private String postName;
    private LocalDate deadline;

    public static ArrayList<RealBookmarkResponse> of(ArrayList<JobPostingDto> arr) {
        ArrayList<RealBookmarkResponse> bookmarkResponses = new ArrayList<>();
        for (JobPostingDto ele : arr) {
            RealBookmarkResponse response = RealBookmarkResponse.builder()
                .companyName(ele.getCompanyName())
                .postName(ele.getPostName())
                .deadline(ele.getDeadline())
                .build();
            bookmarkResponses.add(response);
        }
        return bookmarkResponses;
    }
}
