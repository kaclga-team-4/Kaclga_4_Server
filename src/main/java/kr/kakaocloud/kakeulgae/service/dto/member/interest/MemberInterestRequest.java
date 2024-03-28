package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import java.util.List;
import lombok.Data;

@Data
public class MemberInterestRequest {

    private List<Long> jobDetailIds;
    private List<Long> region2ndIds;
    private List<Long> careerIds;
    private List<Long> educationIds;
    private List<Long> workTypeIds;
}
