package kr.kakaocloud.kakeulgae.domain.repository;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("SELECT b.jobPostingId FROM Bookmark b WHERE b.memberId = :userId")
    ArrayList<Long> findJobPostingIdsByUserId(@Param("userId") Long userId);
}
