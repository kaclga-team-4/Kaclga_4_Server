package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import java.util.List;
import lombok.Data;

@Data
public class MemberInterestRequest {

    private List<String> jobDetails;
    private List<String> region2nds;
    private List<String> careers;
    private List<String> educations;
    private List<String> workTypes;
}
