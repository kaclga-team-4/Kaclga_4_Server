package kr.kakaocloud.kakeulgae.controller;

import java.util.List;

import kr.kakaocloud.kakeulgae.domain.entity.*;
import kr.kakaocloud.kakeulgae.repository.*;
import kr.kakaocloud.kakeulgae.service.dto.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final JobDetailRepository jobDetailRepository;
    private final Region1stRepository region1stRepository;
    private final CareerRepository careerRepository;
    private final WorkTypeRepository workTypeRepository;
    private final EducationRepository educationRepository;
    private final JobCategoryRepository jobCategoryRepository;

    @GetMapping("/categories")
    public CategoryResponse getCategories() {
        List<JobDetail> jobDetailList = jobDetailRepository.findAll();
        List<Career> careerList = careerRepository.findAll();
        List<Region1st> region1stList = region1stRepository.findAll();
        List<WorkType> workTypeList = workTypeRepository.findAll();
        List<Education> educationList = educationRepository.findAll();
        List<JobCategory> jobCategoryList = jobCategoryRepository.findAll();

        return CategoryResponse.builder()
            .jobDetails(jobDetailList)
            .careers(careerList)
            .region(region1stList)
            .workTypes(workTypeList)
            .educations(educationList)
            .jobCategories(jobCategoryList)
            .build();
    }
}
