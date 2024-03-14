package kr.kakaocloud.kakeulgae.service.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import lombok.Data;

@Data
public class BookmarkDto {
    private String companyName;
    private String postName;
    private String career;
    private String education;
    private String url;
    private String workType;
    private LocalDateTime deadline;
    private LocalDateTime createdDate;
    private ArrayList<JobDetail> jobDetails;
}
