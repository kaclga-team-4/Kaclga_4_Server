package kr.kakaocloud.kakeulgae.service.dto.category;

import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import lombok.Data;

@Data
public class CategoryWorkTypeDto {

    private Long id;
    private String type;

    public CategoryWorkTypeDto(WorkType workType) {
        this.id = workType.getId();
        this.type = workType.getType();
    }
}
