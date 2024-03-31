package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import java.util.List;
import lombok.Data;

@Data
public class MemberInterestRequest {

    private List<Long> jobDetail;
    private List<Long> career;
    private List<Long> education;
    private List<Long> workType;
}
