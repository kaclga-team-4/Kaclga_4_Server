package kr.kakaocloud.kakeulgae.service.dto.category;

import java.util.List;

import kr.kakaocloud.kakeulgae.domain.entity.*;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoryResponse {

    private List<String> job_detail;
    private List<RegionCategoryResponse> region;
    private List<String> career;
    private List<String> education;
    private List<String> work_type;
    private List<String> jobCategory;

    @Builder
    public CategoryResponse(List<JobDetail> jobDetails, List<Region1st> region,
        List<Career> careers, List<Education> educations, List<WorkType> workTypes, List<JobCategory> jobCategories) {
        this.job_detail = jobDetails.stream().map(JobDetail::getType).toList();
        this.region = region.stream()
            .map(RegionCategoryResponse::new)
            .toList();
        this.career = careers.stream().map(Career::getType).toList();
        this.education = educations.stream().map(Education::getType).toList();
        this.work_type = workTypes.stream().map(WorkType::getType).toList();
        this.jobCategory = jobCategories.stream().map(JobCategory::getType).toList();
    }
}
