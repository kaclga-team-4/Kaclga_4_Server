package kr.kakaocloud.kakeulgae.service.dto.category;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobCategory;
import lombok.Data;

@Data
public class CategoryJobCategory {

    private Long id;
    private String type;
    private List<CategoryJobDetailDto> jobDetail;

    public CategoryJobCategory(JobCategory jobCategory) {
        this.id = jobCategory.getId();
        this.type = jobCategory.getType();
        this.jobDetail = jobCategory.getJobDetails().stream()
            .map(CategoryJobDetailDto::new)
            .toList();
    }
}
