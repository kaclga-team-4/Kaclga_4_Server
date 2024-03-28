package kr.kakaocloud.kakeulgae.service.dto.category;

import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import lombok.Data;

@Data
public class CategoryJobDetailDto {

    private Long id;
    private String type;

    public CategoryJobDetailDto(JobDetail jobDetail) {
        this.id = jobDetail.getId();
        this.type = jobDetail.getType();
    }
}
