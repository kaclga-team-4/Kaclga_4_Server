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
    Bookmark findByMemberIdAndJobPostingId(Long memberId, Long jobPostingId);
}
