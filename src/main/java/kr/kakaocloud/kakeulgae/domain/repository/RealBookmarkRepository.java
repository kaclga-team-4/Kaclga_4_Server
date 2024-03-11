package kr.kakaocloud.kakeulgae.domain.repository;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.ArrayList;

public interface RealBookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("SELECT b.jobPosting.id FROM Bookmark b WHERE b.member.id = :userId")
    ArrayList<Long> findJobPostingIdsByUserId(@Param("userId") Long userId);
}
