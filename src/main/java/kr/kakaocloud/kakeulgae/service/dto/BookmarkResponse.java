package kr.kakaocloud.kakeulgae.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {

    //private Long jobPostingId;
    private String companyName;
    private String postName;
    private LocalDate deadline;

}
