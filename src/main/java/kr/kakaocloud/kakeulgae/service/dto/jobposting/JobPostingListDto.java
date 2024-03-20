package kr.kakaocloud.kakeulgae.service.dto.jobposting;

import java.time.LocalDate;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import lombok.Data;

@Data
public class JobPostingListDto {

    private String companyName;
    private String postName;
    private String url;
    private LocalDate deadline;
    private LocalDate createdAt;
    private String educationType;
    private List<String> jobDetailType;

    public JobPostingListDto(JobPosting jobPosting) {
        companyName = jobPosting.getCompanyName();
        postName = jobPosting.getPostName();
        url = jobPosting.getUrl();
        deadline = jobPosting.getDeadline();
        createdAt = jobPosting.getCreatedAt();
        educationType = jobPosting.getEducation().getType();
        jobDetailType = jobPosting.getJobDetailPostingRelations()
            .stream().map(jdpr -> jdpr.getJobDetail().getType())
            .toList();
    }
}
