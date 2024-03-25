package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import lombok.Data;

@Data
public class JobDetailInterestDto {

    private Long id;
    private String type;

    public JobDetailInterestDto(JobDetail jobDetail) {
        this.id = jobDetail.getId();
        this.type = jobDetail.getType();
    }
}
