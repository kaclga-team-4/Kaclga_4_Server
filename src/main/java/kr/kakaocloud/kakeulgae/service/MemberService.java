package kr.kakaocloud.kakeulgae.service;

import java.util.NoSuchElementException;
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

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CareerService careerService;
    private final JobDetailService jobDetailService;
    private final RegionService regionService;
    private final WorkTypeService workTypeService;
    private final EducationService educationService;


    public MemberSimpleResponse getSimpleInformation(Long userId) {
        Member findMember = findMember(userId);
        return new MemberSimpleResponse(findMember);
    }

    @Transactional(readOnly = true)
    public MemberResponse getInformation(Long userId) {
        Member findMember = findMember(userId);
        return new MemberResponse(findMember);
    }

    @Transactional
    public void updateInformation(Long userId, MemberUpdateReqeust request) {
        Member findMember = findMember(userId);
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
        Member findMember = findMember(userId);
        findMember.setNoticeCheck(request.getNoticeCheck());
        memberRepository.save(findMember);
    }

    public MemberInterestResponse createUserInterest(Long memberId, MemberInterestRequest request) {
        Member findMember = findMember(memberId);

        jobDetailService.saveUserJobDetail(findMember, request.getJobDetails());
        regionService.saveUserRegion(findMember, request.getRegion2nds());
        careerService.saveUserCareer(findMember, request.getCareers());
        educationService.saveUserEducation(findMember, request.getEducations());
        workTypeService.saveUserWorkType(findMember, request.getWorkTypes());

        return MemberInterestResponse.builder()
            .jobDetails(request.getJobDetails())
            .region2nds(request.getRegion2nds())
            .careers(request.getCareers())
            .educations(request.getEducations())
            .workTypes(request.getWorkTypes())
            .build();
    }

    public MemberInterestResponse updateUserInterest(Long memberId, MemberInterestRequest request) {
        Member findMember = findMember(memberId);

        jobDetailService.updateUserJobDetail(findMember, request.getJobDetails());
        regionService.updateUserRegion(findMember, request.getRegion2nds());
        careerService.updateUserCareer(findMember, request.getCareers());
        educationService.updateUserEducation(findMember, request.getEducations());
        workTypeService.updateUserWorkType(findMember, request.getWorkTypes());

        return MemberInterestResponse.builder()
            .jobDetails(request.getJobDetails())
            .region2nds(request.getRegion2nds())
            .careers(request.getCareers())
            .educations(request.getEducations())
            .workTypes(request.getWorkTypes())
            .build();
    }

    private Member findMember(Long userId) {
        Member findMember = memberRepository.findById(userId).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));
        return findMember;
    }
}
