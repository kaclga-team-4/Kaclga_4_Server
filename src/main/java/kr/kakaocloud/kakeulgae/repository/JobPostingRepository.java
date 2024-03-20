package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.service.dto.JobPostingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    // 사용자id와 bookmark DB의 memberId와 일치하는 인덱스를 받는다
    // -> JobPosting DB에 인덱스와 일치하는 공고 데이터를 가져온다
//    @Query("SELECT new kr.kakaocloud.kakeulgae.service.dto.JobPostingDto(a.companyName, a.postName, a.deadline) \n"
//        + "FROM JobPosting a \n"
//        + "JOIN Bookmark b ON a.id = b.jobPosting.id \n"
//        + "WHERE b.member.id = :userId")
//    Slice<JobPostingDto> findJobPostingIdsByUserIdToSlice(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT jp from JobPosting jp"
        + " join fetch jp.bookmarks bmk"
        + " where bmk.member.id=:userId")
    Slice<JobPosting> findJobPostingIdsByUserIdToSlice(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT jpcr.career.type from JobPostingCareer jpcr where jpcr.jobPosting.id=:postingId")
    List<String> findJobPostingByJobPostingCareers(@Param("postingId") Long postingId);

    @Query("SELECT jpwt.workType.type from JobPostingWorkType jpwt where jpwt.jobPosting.id=:postingId")
    List<String> findTypesByPostingId(@Param("postingId") Long postingId);

    @Query("SELECT jdpr.jobDetail.type from JobDetailPostingRelation jdpr where jdpr.jobPosting.id=:postingId")
    List<String> findJobPostingByJobPostingJobs(@Param("postingId") Long postingId);

    /*
    @Query("SELECT new kr.kakaocloud.kakeulgae.service.dto.JobPostingDto(a.companyName, a.postName, a.deadline) \n"
        + "FROM JobPosting a \n"
        + "JOIN Bookmark b ON a.id = b.jobPosting.id \n"
        + "WHERE b.member.id = :userId AND (a.companyName LIKE CONCAT('%',:keyword,'%') OR a.postName LIKE CONCAT('%',:keyword,'%'))")

    @Query("SELECT jp from JobPosting jp"
        + " join fetch jp.bookmarks bmk"
        + " join fetch jp.education e"
        + " where bmk.member.id=:userId")
    Slice<JobPostingDto> findSearchJobPostingIdsByUserIdToSlice(@Param("userId") Long userId, @Param("keyword") String keyword, Pageable pageable);
    */
}
