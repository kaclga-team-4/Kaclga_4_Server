package kr.kakaocloud.kakeulgae.repository.PreferenceJob;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetailPostingRelation;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.JobPostingCareer;
import kr.kakaocloud.kakeulgae.domain.entity.JobPostingWorkType;
import kr.kakaocloud.kakeulgae.domain.entity.RegionPostingRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.QCareerMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.QEducationMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.QPreferenceJob;
import kr.kakaocloud.kakeulgae.domain.entity.member.QRegionMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.QWorkTypeMemberRelation;
import org.springframework.stereotype.Repository;

@Repository
public class PreferenceJobRepositoryImpl implements PreferenceJobRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PreferenceJobRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Member> findMemberByJobPosting(JobPosting jobPosting) {
        QPreferenceJob preferenceJob = QPreferenceJob.preferenceJob;
        QEducationMemberRelation educationMemberRelation = QEducationMemberRelation.educationMemberRelation;
        QCareerMemberRelation careerMemberRelation = QCareerMemberRelation.careerMemberRelation;
        QRegionMemberRelation regionMemberRelation = QRegionMemberRelation.regionMemberRelation;
        QWorkTypeMemberRelation workTypeMemberRelation = QWorkTypeMemberRelation.workTypeMemberRelation;
        return queryFactory.select(preferenceJob.member)
            .from(preferenceJob)
            .join(educationMemberRelation)
            .on(preferenceJob.member.eq(educationMemberRelation.member))
            .join(careerMemberRelation).on(preferenceJob.member.eq(careerMemberRelation.member))
            .join(regionMemberRelation).on(preferenceJob.member.eq(regionMemberRelation.member))
            .join(workTypeMemberRelation).on(preferenceJob.member.eq(workTypeMemberRelation.member))
            .where(preferenceJob.jobDetail.in(jobPosting.getJobDetailPostingRelations().stream()
                    .map(JobDetailPostingRelation::getJobDetail).toList())
                .and(educationMemberRelation.education.eq(jobPosting.getEducation()))
                .and(careerMemberRelation.career.in(
                    jobPosting.getJobPostingCareers().stream().map(JobPostingCareer::getCareer)
                        .toList()))
                .and(regionMemberRelation.region2nd.in(
                    jobPosting.getRegionPostingRelations().stream()
                        .map(RegionPostingRelation::getRegion2nd).toList()))
                .and(workTypeMemberRelation.workType.in(jobPosting.getJobPostingWorkTypes().stream()
                    .map(JobPostingWorkType::getWorkType).toList())))
            .fetch();
    }
}
