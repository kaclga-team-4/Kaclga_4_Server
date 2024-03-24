package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import java.util.List;
import lombok.Data;

@Data
public class MemberInterestResponse {

    private List<String> jobs;
    private List<String> region1sts;
    private List<String> region2nds;
    private List<String> careers;
    private List<String> educations;
    private List<String> workTypes;
}
