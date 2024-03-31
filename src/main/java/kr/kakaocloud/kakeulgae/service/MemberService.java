package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.Education;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.Region2nd;
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberSimpleResponse;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberUpdateNotification;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberUpdateReqeust;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestRequest;
import kr.kakaocloud.kakeulgae.service.dto.member.interest.MemberInterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CareerService careerService;
    private final JobDetailService jobDetailService;
    private final RegionService regionService;
    private final WorkTypeService workTypeService;
    private final EducationService educationService;
    private final FileService fileService;


    public MemberSimpleResponse getSimpleInformation(Long userId) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
        return new MemberSimpleResponse(findMember);
    }

    @Transactional(readOnly = true)
    public MemberResponse getInformation(Long userId) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
        return new MemberResponse(findMember);
    }

    @Transactional
    public void updateInformation(Long userId, MemberUpdateReqeust request) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
        if (request.getNickname() != null) {
            findMember.setNickname(request.getNickname());
        }
        if (request.getEmail() != null) {
            findMember.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            findMember.setPhoneNumber(request.getPhoneNumber());
        }
        memberRepository.save(findMember);
    }

    @Transactional
    public void updateNotification(Long userId, MemberUpdateNotification request) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
        findMember.setNoticeCheck(request.getNoticeCheck());
        memberRepository.save(findMember);
    }

    @Transactional
    public MemberInterestResponse createUserInterest(Long id, MemberInterestRequest request) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));

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

    @Transactional
    public String updateProfile(Long userId, MultipartFile profileImage) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
        findMember.getProfile().changeProfile(fileService.saveProfile(profileImage));
        memberRepository.save(findMember);
        return findMember.getProfile().getUrl();
    }
}
