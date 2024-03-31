package kr.kakaocloud.kakeulgae.service.dto.category;

import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import lombok.Data;

@Data
public class CategoryEducationDto {
    private Long id;
    private String type;

    public CategoryEducationDto(Education education) {
        this.id = education.getId();
        this.type = education.getType();
    }
}
