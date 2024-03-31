package kr.kakaocloud.kakeulgae.service.dto.category;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.Region1st;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoryResponse {

    private List<CategoryJobDetailDto> jobDetails;
    private List<CategoryRegionResponse> region;
    private List<CategoryCareerDto> career;
    private List<CategoryEducationDto> education;
    private List<CategoryWorkTypeDto> work_type;

    @Builder
    public CategoryResponse(List<JobDetail> jobDetails,
        List<Region1st> region,
        List<Career> careers, List<Education> educations, List<WorkType> workTypes) {
        this.jobDetails = jobDetails.stream().map(CategoryJobDetailDto::new).toList();
        this.region = region.stream()
            .map(CategoryRegionResponse::new)
            .toList();
        this.career = careers.stream().map(CategoryCareerDto::new).toList();
        this.education = educations.stream().map(CategoryEducationDto::new).toList();
        this.work_type = workTypes.stream().map(CategoryWorkTypeDto::new).toList();
    }
}
