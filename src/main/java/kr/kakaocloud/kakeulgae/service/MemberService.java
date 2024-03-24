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

    public MemberInterestResponse createUserInterest(Long id, MemberInterestRequest request) {
        /**
         * List<String> jobDetails;
         * List<String> region_2nds;
         * List<String> careers;
         * List<String> educations;
         * List<String> workTypes;
         */
        Member member = memberRepository.findById(id).orElseThrow(() ->
            new NoSuchElementException("해당 유저가 존재하지 않습니다"));

        jobDetailService.saveUserJobDetail(member, request.getJobDetails());
        regionService.saveUserRegion(member, request.getRegion2nds());
        careerService.saveUserCareer(member, request.getCareers());
        educationService.saveUserEducation(member, request.getEducations());
        workTypeService.saveUserWorkType(member, request.getWorkTypes());

        MemberInterestResponse response = MemberInterestResponse.builder()
            .jobDetails(request.getJobDetails())
            .region2nds(request.getRegion2nds())
            .careers(request.getCareers())
            .educations(request.getEducations())
            .workTypes(request.getWorkTypes())
            .build();
        return response;
    }
}
