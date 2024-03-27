package kr.kakaocloud.kakeulgae.controller;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.Region1st;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import kr.kakaocloud.kakeulgae.repository.CareerRepository;
import kr.kakaocloud.kakeulgae.repository.EducationRepository;
import kr.kakaocloud.kakeulgae.repository.JobDetailRepository;
import kr.kakaocloud.kakeulgae.repository.Region1stRepository;
import kr.kakaocloud.kakeulgae.repository.WorkTypeRepository;
import kr.kakaocloud.kakeulgae.security.LoginUserId;
import kr.kakaocloud.kakeulgae.service.dto.category.CategoryResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestRequest;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final JobDetailRepository jobDetailRepository;
    private final Region1stRepository region1stRepository;
    private final CareerRepository careerRepository;
    private final WorkTypeRepository workTypeRepository;
    private final EducationRepository educationRepository;

    @GetMapping("/categories")
    public CategoryResponse getCategories() {
        List<JobDetail> jobDetailList = jobDetailRepository.findAll();
        List<Career> careerList = careerRepository.findAll();
        List<Region1st> region1stList = region1stRepository.findAll();
        List<WorkType> workTypeList = workTypeRepository.findAll();
        List<Education> educationList = educationRepository.findAll();

        return CategoryResponse.builder()
            .jobDetails(jobDetailList)
            .careers(careerList)
            .region(region1stList)
            .workTypes(workTypeList)
            .educations(educationList)
            .build();
    }

    @PostMapping("/interest/create")
    public MemberInterestResponse createInterest(@LoginUserId Long id,
        @ModelAttribute MemberInterestRequest request) {
        return memberService.createUserInterest(id, request);
    }
}
