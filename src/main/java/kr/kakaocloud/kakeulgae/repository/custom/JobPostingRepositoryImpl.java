package kr.kakaocloud.kakeulgae.repository.custom;

import static kr.kakaocloud.kakeulgae.domain.entity.QBookmark.bookmark;
import static kr.kakaocloud.kakeulgae.domain.entity.QEducation.education;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobDetail.jobDetail;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobDetailPostingRelation.jobDetailPostingRelation;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobPosting.jobPosting;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobPostingCareer.jobPostingCareer;
import static kr.kakaocloud.kakeulgae.domain.entity.QJobPostingWorkType.jobPostingWorkType;
import static kr.kakaocloud.kakeulgae.domain.entity.QRegionPostingRelation.regionPostingRelation;
import static kr.kakaocloud.kakeulgae.domain.entity.member.QMember.member;
import static kr.kakaocloud.kakeulgae.domain.entity.member.QPreferenceJob.preferenceJob;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.QBookmark;
import kr.kakaocloud.kakeulgae.domain.entity.QJobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.QJobDetailPostingRelation;
import kr.kakaocloud.kakeulgae.domain.entity.QJobPosting;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;


public class JobPostingRepositoryImpl extends QuerydslRepositorySupport implements
    JobPostingRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public JobPostingRepositoryImpl(EntityManager em) {
        super(JobPosting.class);
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<JobPosting> search(Long memberId, JobPostingSearchCondition condition,
        Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (condition.getCareers() != null) {
            builder.or(jobPostingCareer.career.id.in(condition.getCareers()));
        }
        if (condition.getJobDetails() != null) {
            builder.or(jobDetailPostingRelation.jobDetail.id.in(condition.getJobDetails()));
        }
        if (condition.getWorkTypes() != null) {
            builder.or(jobPostingWorkType.workType.id.in(condition.getWorkTypes()));
        }
        if (condition.getEducation() != null) {
            builder.or(jobPosting.education.id.eq(condition.getEducation()));
        }

        return queryFactory
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
    }

    public Predicate eqKeyword(String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(keyword)) {
            QJobPosting jobPosting = QJobPosting.jobPosting;
            QJobDetailPostingRelation relation = QJobDetailPostingRelation.jobDetailPostingRelation;
            QJobDetail jobDetail = QJobDetail.jobDetail;

            builder.or(jobPosting.companyName.containsIgnoreCase(keyword));
            builder.or(jobPosting.postName.containsIgnoreCase(keyword));

            builder.or(jobDetail.type.containsIgnoreCase(keyword)
                .and(relation.jobPosting.id.eq(jobPosting.id))
                .and(relation.jobDetail.id.eq(jobDetail.id)));
        }

        return builder.getValue();
    }

    @Override
    public Slice<JobPosting> findBySearchBookmarkData(Long id, @Param("keyword") String keyword,
        Pageable pageable) {

        String orderString = pageable.getSort().iterator().next().getProperty();
        OrderSpecifier<LocalDate> orderSpecifier = QJobPosting.jobPosting.createdAt.asc();

        if (orderString.equals("deadline")) {
            orderSpecifier = QJobPosting.jobPosting.deadline.asc();
        }

        List<JobPosting> content = queryFactory
            .selectFrom(QJobPosting.jobPosting)
            .innerJoin(QJobPosting.jobPosting.bookmarks, QBookmark.bookmark)
            .leftJoin(QJobPosting.jobPosting.jobDetailPostingRelations,
                QJobDetailPostingRelation.jobDetailPostingRelation)
            .leftJoin(QJobDetailPostingRelation.jobDetailPostingRelation.jobDetail,
                QJobDetail.jobDetail)
            .where(
                QBookmark.bookmark.member.id.eq(id),
                eqKeyword(keyword)
            )
            .orderBy(orderSpecifier)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

    @Override
    public Slice<JobPosting> findBySearchJobData(@Param("keyword") String keyword, Pageable pageable){

        String orderStrings = pageable.getSort().iterator().next().getProperty();
        OrderSpecifier<LocalDate> orderSpecifier = QJobPosting.jobPosting.createdAt.asc();

        if(orderStrings.equals("deadline")) {
            orderSpecifier = QJobPosting.jobPosting.deadline.asc();
        }

        List<JobPosting> content = queryFactory
            .selectFrom(QJobPosting.jobPosting)
            .leftJoin(QJobPosting.jobPosting.jobDetailPostingRelations, QJobDetailPostingRelation.jobDetailPostingRelation)
            .leftJoin(QJobDetailPostingRelation.jobDetailPostingRelation.jobDetail, QJobDetail.jobDetail)
            .where(
                eqKeyword(keyword)
            )
            .orderBy(orderSpecifier)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

}
