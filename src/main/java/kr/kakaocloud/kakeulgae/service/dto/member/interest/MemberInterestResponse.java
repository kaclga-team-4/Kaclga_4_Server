package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberInterestResponse {

    private List<String> jobDetails;
    private List<String> region2nds;
    private List<String> careers;
    private List<String> educations;
    private List<String> workTypes;
}
