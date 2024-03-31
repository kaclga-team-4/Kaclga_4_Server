package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import kr.kakaocloud.kakeulgae.domain.entity.Education;
import lombok.Data;

@Data
public class EducationInterestDto {

    private Long id;
    private String type;

    public EducationInterestDto(Education education) {
        this.id = education.getId();
        this.type = education.getType();
    }

}
