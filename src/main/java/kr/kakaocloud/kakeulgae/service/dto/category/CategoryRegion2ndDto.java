package kr.kakaocloud.kakeulgae.service.dto.category;

import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import lombok.Data;

@Data
public class CategoryRegion2ndDto {

    private Long id;
    private String type;

    public CategoryRegion2ndDto(Region2nd region2nd) {
        this.id = region2nd.getId();
        this.type = region2nd.getType();
    }
}
