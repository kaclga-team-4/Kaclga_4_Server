package kr.kakaocloud.kakeulgae.service.dto.category;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Region1st;
import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import lombok.Data;

@Data
public class RegionCategoryResponse {

    private String region1st;
    private List<String> region2nds;

    public RegionCategoryResponse(Region1st region1st) {
        this.region1st = region1st.getType();
        this.region2nds = region1st.getRegion2nds().stream()
            .map(Region2nd::getType)
            .toList();
    }
}
