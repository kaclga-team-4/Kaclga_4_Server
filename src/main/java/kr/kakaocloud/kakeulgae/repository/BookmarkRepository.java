package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    // 사용자id와 bookmark DB의 memberId와 일치하는 인덱스를 받는다
    // -> JobPosting DB에 인덱스와 일치하는 공고 데이터를 가져온다
    @Query("SELECT a.companyName, a.postName, a.deadline \n"
        + "FROM JobPosting a \n"
        + "JOIN Bookmark b ON a.id = b.jobPosting.id \n"
        + "WHERE b.member.id = :userId")
    Slice<Bookmark> findJobPostingIdsByUserIdToSlice(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT a.companyName, a.postName, a.deadline \n"
        + "FROM JobPosting a \n"
        + "JOIN Bookmark b ON a.id = b.jobPosting.id \n"
        + "WHERE b.member.id = :userId AND (a.companyName LIKE CONCAT('%',:keyword,'%') OR a.postName LIKE CONCAT('%',:keyword,'%'))")
    Slice<Bookmark> findSearchJobPostingIdsByUserIdToSlice(@Param("userId") Long userId, @Param("keyword") String keyword, Pageable pageable);
}
