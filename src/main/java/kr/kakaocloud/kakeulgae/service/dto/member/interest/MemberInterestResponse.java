package kr.kakaocloud.kakeulgae.service.dto.member.interest;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberInterestResponse {

    private List<JobDetailInterestDto> jobDetails;
    private List<CareerInterestDto> careers;
    private List<EducationInterestDto> educations;
    private List<WorkTypeInterestDto> workTypes;

    @Builder
    public MemberInterestResponse(List<JobDetail> jobDetails,
        List<Career> careers, List<Education> educations, List<WorkType> workTypes) {
        this.jobDetails = jobDetails.stream()
            .map(JobDetailInterestDto::new).toList();

        this.careers = careers.stream()
            .map(CareerInterestDto::new).toList();

        this.educations = educations.stream()
            .map(EducationInterestDto::new).toList();

        this.workTypes = workTypes.stream()
            .map(WorkTypeInterestDto::new).toList();
    }
}
