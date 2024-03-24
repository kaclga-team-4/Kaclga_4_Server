package kr.kakaocloud.kakeulgae.service.dto.category;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Region1st;
import lombok.Data;

@Data
public class RegionCategoryResponse {

    private String region1st;
    private List<String> region2nds;

    public RegionCategoryResponse(Region1st region1st) {
        this.region1st = region1st.getType();
    }
}
