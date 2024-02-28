package kr.kakaocloud.kakeulgae.domain.dto;

import java.time.LocalDate;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import lombok.Data;

@Data
public class JobPostingListDto {

    private String companyName;
    private String postName;
    private String job;
    private String career;
    private String education;
    private String url;
    private String workType;
    private LocalDate deadline;
    private LocalDate createdDate;

    public JobPostingListDto(JobPosting jobPosting) {
        this.companyName = jobPosting.getCompanyName();
        this.postName = jobPosting.getPostName();
        this.job = jobPosting.getJob().getType();
        this.career = jobPosting.getCareer().getType();
        this.education = jobPosting.getEducation().getType();
        this.url = jobPosting.getUrl();
        this.workType = jobPosting.getWorkType().getType();
        this.deadline = jobPosting.getDeadline();
        this.createdDate = jobPosting.getCreatedAt();
    }
}
