package kr.kakaocloud.kakeulgae.service.dto.jobposting;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JobPostingSearchCondition {

    private List<String> region2nds;
    private List<String> workTypes;
    private String education;
    private List<String> careers;
    private List<String> jobDetails;
}
