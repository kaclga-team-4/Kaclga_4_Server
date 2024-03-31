package kr.kakaocloud.kakeulgae.service.dto.jobposting;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JobPostingSearchCondition {

    private List<Long> region2nds;
    private List<Long> workTypes;
    private Long education;
    private List<Long> careers;
    private List<Long> jobDetails;
}
