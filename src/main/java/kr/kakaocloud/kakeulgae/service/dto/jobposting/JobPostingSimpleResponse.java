package kr.kakaocloud.kakeulgae.service.dto.jobposting;

import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobPostingSimpleResponse {

    private Long id;
    private String title;
    private String url;

    public JobPostingSimpleResponse(JobPosting jobPosting) {
        this.id = jobPosting.getId();
        this.title = jobPosting.getPostName();
        this.url = jobPosting.getUrl();
    }
}
