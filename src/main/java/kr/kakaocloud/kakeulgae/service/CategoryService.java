package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
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
    private final WorkTypeService workTypeService;
    private final EducationService educationService;

    @Transactional
    public MemberInterestResponse createUserInterest(Long id, MemberInterestRequest request) {
        Member member = findMember(id);

        List<JobDetail> jobDetails = jobDetailService.saveUserJobDetail(member,
            request.getJobDetails());
        List<Career> careers = careerService.saveUserCareer(member, request.getCareer());
        List<Education> educations = educationService.saveUserEducation(member,
            request.getEducation());
        List<WorkType> workTypes = workTypeService.saveUserWorkType(member,
            request.getWork_type());

        return MemberInterestResponse.builder()
            .jobDetails(jobDetails)
            .careers(careers)
            .educations(educations)
            .workTypes(workTypes)
            .build();
    }

    public MemberInterestResponse updateUserInterest(Long memberId, MemberInterestRequest request) {
        Member findMember = findMember(memberId);

        List<JobDetail> jobDetails = jobDetailService.updateUserJobDetail(findMember,
            request.getJobDetails());
        List<Career> careers = careerService.updateUserCareer(findMember, request.getCareer());
        List<Education> educations = educationService.updateUserEducation(findMember,
            request.getEducation());
        List<WorkType> workTypes = workTypeService.updateUserWorkType(findMember,
            request.getWork_type());

        return MemberInterestResponse.builder()
            .jobDetails(jobDetails)
            .careers(careers)
            .educations(educations)
            .workTypes(workTypes)
            .build();
    }

    public CategoryResponse findAllCategories() {
        List<JobDetail> jobDetailList = jobDetailService.findAllJobDetails();
        List<Career> careerList = careerService.findAllCareer();
        List<WorkType> workTypeList = workTypeService.findAllWorkType();
        List<Education> educationList = educationService.findAllEducation();

        return CategoryResponse.builder()
            .jobDetails(jobDetailList)
            .careers(careerList)
            .workTypes(workTypeList)
            .educations(educationList)
            .build();
    }

    private Member findMember(Long userId) {
        return memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
    }
}
