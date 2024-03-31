package kr.kakaocloud.kakeulgae.service.dto.jobposting;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class JobPostingSearchResponse {

    private String companyName;
    private String postName;
    private String url;
    private LocalDate deadline;
    private LocalDate createdAt;
    private String educationType;
    private List<String> jobDetailTypes;
    private List<String> workTypes;
    private List<String> careers;

    public JobPostingSearchResponse(String companyName, String postName, String url,
        LocalDate deadline,
        LocalDate createdAt, String educationType, List<String> jobDetailTypes,
        List<String> workTypes,
        List<String> careers) {
        this.companyName = companyName;
        this.postName = postName;
        this.url = url;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.educationType = educationType;
        this.jobDetailTypes = jobDetailTypes;
        this.workTypes = workTypes;
        this.careers = careers;
    }
}
