package kr.kakaocloud.kakeulgae.repository;

import java.util.Optional;
import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByMemberAndJobPosting(Member member, JobPosting jobPosting);

    Bookmark findByMemberIdAndJobPostingId(Long memberId, Long jobPostingId);

    boolean existsByMemberIdAndJobPostingId(Long memberId, Long jobPostingId);
}
