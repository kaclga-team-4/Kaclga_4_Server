package kr.kakaocloud.kakeulgae.domain.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class JobPostingDto {
    private String companyName;
    private String postName;
    private LocalDate deadline;

    public JobPostingDto(String companyName, String postName, LocalDate deadline){
        this.companyName = companyName;
        this.postName = postName;
        this.deadline = deadline;
    }
}
