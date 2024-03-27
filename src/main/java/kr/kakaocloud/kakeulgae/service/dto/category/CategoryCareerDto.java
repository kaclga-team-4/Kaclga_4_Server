package kr.kakaocloud.kakeulgae.service.dto.category;

import kr.kakaocloud.kakeulgae.domain.entity.Career;
import lombok.Data;

@Data
public class CategoryCareerDto {

    private Long id;
    private String type;

    public CategoryCareerDto(Career career) {
        this.id = career.getId();
        this.type = career.getType();
    }
}
