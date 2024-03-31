package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import kr.kakaocloud.kakeulgae.domain.entity.Career;
import lombok.Data;

@Data
public class CareerInterestDto {

    private Long id;
    private String type;

    public CareerInterestDto(Career career) {
        this.id = career.getId();
        this.type = career.getType();
    }
}
