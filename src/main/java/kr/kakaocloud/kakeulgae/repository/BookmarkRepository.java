package kr.kakaocloud.kakeulgae.repository;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Bookmark findByMemberIdAndJobPostingId(Long memberId, Long jobPostingId);

    boolean existsByMemberIdAndJobPostingId(Long memberId, Long jobPostingId);
}
