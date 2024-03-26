package kr.kakaocloud.kakeulgae.service.dto.category;

import kr.kakaocloud.kakeulgae.domain.entity.JobCategory;
import lombok.Data;

@Data
public class CategoryJobCategory {

    private Long id;
    private String type;

    public CategoryJobCategory(JobCategory jobCategory) {
        this.id = jobCategory.getId();
        this.type = jobCategory.getType();
    }
}
