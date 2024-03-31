package kr.kakaocloud.kakeulgae.service.dto.member.interest;


import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import lombok.Data;

@Data
public class WorkTypeInterestDto {

    private Long id;
    private String type;

    public WorkTypeInterestDto(WorkType workType) {
        this.id = workType.getId();
        this.type = workType.getType();
    }
}
