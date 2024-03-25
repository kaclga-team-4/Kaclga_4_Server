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

@Entity
@Table(
    name = "preference_job",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "preference_job_member_id_job_detail_id_unique",
            columnNames = {"member_id", "job_detail_id"}
        ),
    }
)
public class PreferenceJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_preference_member_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_detail_id", foreignKey = @ForeignKey(name = "fk_preference_job_detail_id"))
    private JobDetail jobDetail;
}
