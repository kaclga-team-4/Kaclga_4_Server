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
import kr.kakaocloud.kakeulgae.service.CategoryService;
import kr.kakaocloud.kakeulgae.service.dto.category.CategoryResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestRequest;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public CategoryResponse getCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping("/interest/create")
    public MemberInterestResponse createInterest(@LoginUserId Long id,
        @RequestBody MemberInterestRequest request) {
        return categoryService.createUserInterest(id, request);
    }

    @PutMapping("/interest/update")
    public MemberInterestResponse updateInterest(@LoginUserId Long id,
        @RequestBody MemberInterestRequest request) {
        return categoryService.updateUserInterest(id, request);
    }
}
