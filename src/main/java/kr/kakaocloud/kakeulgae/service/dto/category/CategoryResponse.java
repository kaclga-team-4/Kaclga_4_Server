package kr.kakaocloud.kakeulgae.service.dto.category;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.Region1st;
import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoryResponse {

    private List<String> job_detail;
    private List<RegionCategoryResponse> region;
    private List<String> career;
    private List<String> education;
    private List<String> work_type;

    @Builder
    public CategoryResponse(List<JobDetail> jobDetails, List<Region1st> region,
        List<Career> careers, List<Education> educations, List<WorkType> workTypes) {
        this.job_detail = jobDetails.stream().map(JobDetail::getType).toList();
        this.region = region.stream()
            .map(RegionCategoryResponse::new)
            .toList();
        this.career = careers.stream().map(Career::getType).toList();
        this.education = educations.stream().map(Education::getType).toList();
        this.work_type = workTypes.stream().map(WorkType::getType).toList();
    }
}
