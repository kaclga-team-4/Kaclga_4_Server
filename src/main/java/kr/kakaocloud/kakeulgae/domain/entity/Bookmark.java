package kr.kakaocloud.kakeulgae.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.support.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            name = "bookmark_member_id_job_posting_id_unique",
            columnNames = {"member_id", "job_posting_id"}
        )
    }
)
public class Bookmark extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_bookmark_member_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id", foreignKey = @ForeignKey(name = "fk_bookmark_job_posting_id"))
    private JobPosting jobPosting;


    public Bookmark(Member member, JobPosting jobPosting) {
        this.member = member;
        this.jobPosting = jobPosting;
    }
}
