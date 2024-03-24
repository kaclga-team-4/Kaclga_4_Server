package kr.kakaocloud.kakeulgae.domain.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_bookmark_member_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id", foreignKey = @ForeignKey(name = "fk_bookmark_job_posting_id"))
    private JobPosting jobPosting;

    @Builder
    public Bookmark(Long id, LocalDateTime created_at, Member member, JobPosting jobPosting) {
        this.id = id;
        this.created_at = created_at;
        this.member = member;
        this.jobPosting = jobPosting;
    }
}
