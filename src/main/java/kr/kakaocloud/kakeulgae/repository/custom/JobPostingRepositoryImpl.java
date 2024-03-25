package kr.kakaocloud.kakeulgae.repository.custom;

import static kr.kakaocloud.kakeulgae.domain.entity.QBookmark.bookmark;
import static kr.kakaocloud.kakeulgae.domain.entity.QEducation.education;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobDetail.jobDetail;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobDetailPostingRelation.jobDetailPostingRelation;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobPosting.jobPosting;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobPostingCareer.jobPostingCareer;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobPostingWorkType.jobPostingWorkType;
import static kr.kakaocloud.kakeulgae.domain.entity.QPreferenceJob.preferenceJob;
import static kr.kakaocloud.kakeulgae.domain.entity.QRegionPostingRelation.regionPostingRelation;
import static kr.kakaocloud.kakeulgae.domain.entity.member.QMember.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;


public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public JobPostingRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

//    @Override
//    public Slice<JobPosting> search(Long memberId, JobPostingSearchCondition condition, Pageable pageable) {
//        BooleanBuilder builder = new BooleanBuilder();
//
//        if (condition.getCareers() != null) {
//            builder.or(careerMemberRelation.career.type.in(condition.getCareers()));
//        }
////        if (condition.getJobDetails() != null) {
////            builder.or(jobDetailPostingRelation.jobDetail.type.in(condition.getJobDetails()));
////        }
////        if (condition.getWorkTypes() != null) {
////            builder.or(jobPostingWorkType.workType.type.in(condition.getWorkTypes()));
////        }
////        if (condition.getRegion2nds() != null) {
////            builder.or(regionPostingRelation.region2nd.type.in(condition.getRegion2nds()));
////        }
////        if (condition.getEducation() != null) {
////            builder.or(jobPosting.education.type.eq(condition.getEducation()));
////        }
//
//        List<JobPosting> content = queryFactory
//            .selectFrom(jobPosting)
//            .join(jobPosting.jobDetailPostingRelations, jobDetailPostingRelation)
//            .join(jobDetailPostingRelation.jobDetail, jobDetail)
//            .join(jobDetail.preferenceJobs, preferenceJob)
//            .join(preferenceJob.member, member)
//            .join(jobPosting.jobPostingCareers, jobPostingCareer)
//            .join(jobPosting.jobPostingWorkTypes, jobPostingWorkType)
//            .join(jobPosting.regionPostingRelations, regionPostingRelation)
//            .leftJoin(jobPosting.bookmarks, bookmark)
//            .join(jobPosting.education, education).fetchJoin()
////            .offset(pageable.getOffset())
////            .limit(pageable.getPageSize())
////            .fetchResults()
////            .getResults();
//            .fetch();
//
//        boolean hasNext = false;
//        int pageSize = pageable.getPageSize();
//        if (content.size() > pageSize) {
//            content.remove(pageSize);
//            hasNext = true;
//        }
//
//        return new SliceImpl<>(content, pageable, hasNext);
//    }

    @Override
    public Slice<JobPosting> search(Long memberId, JobPostingSearchCondition condition,
        Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        System.out.println("condition = " + condition);

        if (condition.getCareers() != null) {
            builder.or(jobPostingCareer.career.type.in(condition.getCareers()));
        }
        if (condition.getJobDetails() != null) {
            builder.or(jobDetailPostingRelation.jobDetail.type.in(condition.getJobDetails()));
        }
        if (condition.getWorkTypes() != null) {
            builder.or(jobPostingWorkType.workType.type.in(condition.getWorkTypes()));
        }
        if (condition.getRegion2nds() != null) {
            builder.or(regionPostingRelation.region2nd.type.in(condition.getRegion2nds()));
        }
        if (condition.getEducation() != null) {
            builder.or(jobPosting.education.type.eq(condition.getEducation()));
        }

        List<JobPosting> content = queryFactory
            .selectFrom(jobPosting)
            .leftJoin(jobPosting.jobDetailPostingRelations, jobDetailPostingRelation)
            .leftJoin(jobDetailPostingRelation.jobDetail, jobDetail)
            .leftJoin(jobDetail.preferenceJobs, preferenceJob)
            .leftJoin(preferenceJob.member, member)
            .leftJoin(jobPosting.jobPostingCareers, jobPostingCareer)
            .leftJoin(jobPosting.jobPostingWorkTypes, jobPostingWorkType)
            .leftJoin(jobPosting.regionPostingRelations, regionPostingRelation)
            .leftJoin(jobPosting.bookmarks, bookmark)
            .leftJoin(jobPosting.education, education).fetchJoin()
            .where(
                member.id.eq(memberId),
                builder
            )
            .fetch();

        for (JobPosting jobPosting : content) {
            System.out.println("jobPosting = " + jobPosting);
        }

        boolean hasNext = false;
        int pageSize = pageable.getPageSize();
        if (content.size() > pageSize) {
            content.remove(pageSize);
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }
}
