package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import lombok.Data;

@Data
public class Region2ndInterestDto {

    private Long id;
    private String type;

    public Region2ndInterestDto(Region2nd region2nd) {
        this.id = region2nd.getId();
        this.type = region2nd.getType();
    }
}
