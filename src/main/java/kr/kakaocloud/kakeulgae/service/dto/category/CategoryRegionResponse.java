package kr.kakaocloud.kakeulgae.service.dto.category;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Region1st;
import lombok.Data;

@Data
public class CategoryRegionResponse {

    private Long region1st_id;
    private String region1st;
    private List<CategoryRegion2ndDto> region2nd;

    public CategoryRegionResponse(Region1st region1st) {
        this.region1st_id = region1st.getId();
        this.region1st = region1st.getType();
        this.region2nd = region1st.getRegion2nds().stream()
            .map(CategoryRegion2ndDto::new)
            .toList();
    }
}
