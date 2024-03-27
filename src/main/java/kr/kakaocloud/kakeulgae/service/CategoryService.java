package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.Region1st;
import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.service.dto.category.CategoryResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestRequest;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final MemberRepository memberRepository;
    private final CareerService careerService;
    private final JobDetailService jobDetailService;
    private final RegionService regionService;
    private final WorkTypeService workTypeService;
    private final EducationService educationService;

    @Transactional
    public MemberInterestResponse createUserInterest(Long id, MemberInterestRequest request) {
        Member member = findMember(id);

        List<JobDetail> jobDetails = jobDetailService.saveUserJobDetail(member,
            request.getJobDetailIds());
        List<Region2nd> region2nds = regionService.saveUserRegion(member,
            request.getRegion2ndIds());
        List<Career> careers = careerService.saveUserCareer(member, request.getCareerIds());
        List<Education> educations = educationService.saveUserEducation(member,
            request.getEducationIds());
        List<WorkType> workTypes = workTypeService.saveUserWorkType(member,
            request.getWorkTypeIds());

        return MemberInterestResponse.builder()
            .jobDetails(jobDetails)
            .region2nds(region2nds)
            .careers(careers)
            .educations(educations)
            .workTypes(workTypes)
            .build();
    }

    public MemberInterestResponse updateUserInterest(Long memberId, MemberInterestRequest request) {
        Member findMember = findMember(memberId);

        List<JobDetail> jobDetails = jobDetailService.updateUserJobDetail(findMember,
            request.getJobDetailIds());
        List<Region2nd> region2nds = regionService.updateUserRegion(findMember,
            request.getRegion2ndIds());
        List<Career> careers = careerService.updateUserCareer(findMember, request.getCareerIds());
        List<Education> educations = educationService.updateUserEducation(findMember,
            request.getEducationIds());
        List<WorkType> workTypes = workTypeService.updateUserWorkType(findMember,
            request.getWorkTypeIds());

        return MemberInterestResponse.builder()
            .jobDetails(jobDetails)
            .region2nds(region2nds)
            .careers(careers)
            .educations(educations)
            .workTypes(workTypes)
            .build();
    }

    private Member findMember(Long userId) {
        return memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
    }

    public CategoryResponse findAllCategories() {
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
}
