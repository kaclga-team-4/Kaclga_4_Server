package kr.kakaocloud.kakeulgae.controller;

import static kr.kakaocloud.kakeulgae.support.MemberFixture.createMember;
import static org.junit.jupiter.api.Assertions.assertEquals;

import kr.kakaocloud.kakeulgae.domain.entity.member.CareerMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.EducationMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.PreferenceJob;
import kr.kakaocloud.kakeulgae.domain.entity.member.RegionMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.WorkTypeMemberRelation;
import kr.kakaocloud.kakeulgae.repository.CareerMemberRepository;
import kr.kakaocloud.kakeulgae.repository.CareerRepository;
import kr.kakaocloud.kakeulgae.repository.EducationMemberRepository;
import kr.kakaocloud.kakeulgae.repository.EducationRepository;
import kr.kakaocloud.kakeulgae.repository.JobDetailPostingRelation;
import kr.kakaocloud.kakeulgae.repository.JobDetailRepository;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.repository.Notification.NotificationRepository;
import kr.kakaocloud.kakeulgae.repository.PreferenceJob.PreferenceJobRepository;
import kr.kakaocloud.kakeulgae.repository.Region2ndMemberRelationRepository;
import kr.kakaocloud.kakeulgae.repository.Region2ndRepository;
import kr.kakaocloud.kakeulgae.repository.WorkTypeMemberRelationRepository;
import kr.kakaocloud.kakeulgae.repository.WorkTypeRepository;
import kr.kakaocloud.kakeulgae.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class NotificationControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    CareerRepository careerRepository;
    @Autowired
    CareerMemberRepository careerMemberRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    EducationMemberRepository educationMemberRepository;
    @Autowired
    JobDetailRepository jobDetailRepository;
    @Autowired
    JobDetailPostingRelation jobDetailPostingRelation;
    @Autowired
    JobPostingRepository jobPostingRepository;
    @Autowired
    Region2ndRepository region2ndRepository;
    @Autowired
    Region2ndMemberRelationRepository region2ndMemberRelationRepository;
    @Autowired
    WorkTypeRepository workTypeRepository;
    @Autowired
    WorkTypeMemberRelationRepository workTypeMemberRelationRepository;
    @Autowired
    PreferenceJobRepository preferenceJobRepository;

    @Autowired
    NotificationService sut;

    @BeforeEach
    public void setUp() {
        Member member = createMember();
        memberRepository.save(member);
        CareerMemberRelation careerMemberRelation = CareerMemberRelation.createRelation(member,
            careerRepository.findById(1L).get());
        careerMemberRepository.save(careerMemberRelation);
        EducationMemberRelation educationMemberRelation = EducationMemberRelation.createRelation(
            member, educationRepository.findById(1L).get());
        educationMemberRepository.save(educationMemberRelation);
        PreferenceJob preferenceJob = PreferenceJob.createRelation(member,
            jobDetailRepository.findById(1L).get());
        preferenceJobRepository.save(preferenceJob);
        RegionMemberRelation regionMemberRelation = RegionMemberRelation.createRelation(member,
            region2ndRepository.findById(1L).get());
        region2ndMemberRelationRepository.save(regionMemberRelation);
        WorkTypeMemberRelation workTypeMemberRelation = WorkTypeMemberRelation.createRelation(
            member, workTypeRepository.findById(1L).get());
        workTypeMemberRelationRepository.save(workTypeMemberRelation);
    }

    @Test
    public void createNotification() {
        // given
        assertEquals(0, notificationRepository.findAll().size());
        // when
        sut.createNotification();
        // then
        assertEquals(1, notificationRepository.findAll().size());
    }
}
