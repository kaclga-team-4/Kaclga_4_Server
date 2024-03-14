package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query("SELECT b.jobPosting.id FROM Bookmark b WHERE b.member.id = :userId")
    ArrayList<Long> findJobPostingIdsByUserId(@Param("userId") Long userId);
}
