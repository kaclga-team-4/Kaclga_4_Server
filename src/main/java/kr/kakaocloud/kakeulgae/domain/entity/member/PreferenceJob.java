package kr.kakaocloud.kakeulgae.domain.entity.member;

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
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.UniqueConstraint;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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


    public static PreferenceJob createRelation(Member member, JobDetail jobDetail) {
        PreferenceJob relation = new PreferenceJob();
        relation.member = member;
        relation.jobDetail = jobDetail;

        return relation;
    }
}
