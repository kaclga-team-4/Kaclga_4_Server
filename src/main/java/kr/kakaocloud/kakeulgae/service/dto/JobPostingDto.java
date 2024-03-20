package kr.kakaocloud.kakeulgae.service.dto;


import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobPostingDto {
    private Long id;
    private String companyName;
    private String postName;
    private LocalDate createAt;
    private LocalDate deadline;
    private String education;
    private List<String> career;
    private List<String> job;
    private List<String> workTypes;

    public JobPostingDto(JobPosting jobPosting){
        this.id = jobPosting.getId();
        this.companyName = jobPosting.getCompanyName();
        this.postName = jobPosting.getPostName();
        this.createAt = jobPosting.getCreatedAt();
        this.deadline = jobPosting.getDeadline();
        this.education = jobPosting.getEducation().getType();
        //this.career = jobPosting.getCareer().getType();
        //this.job = jobPosting.getJob().getType();
    }
}
