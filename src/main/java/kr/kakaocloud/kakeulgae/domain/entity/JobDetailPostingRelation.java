package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class JobDetailPostingRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id",
        foreignKey = @ForeignKey(name = "fk_job_detail_posting_relation_job_posting_id"))
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_detail_id",
        foreignKey = @ForeignKey(name = "fk_job_detail_posting_relation_job_detail_id"))
    private JobDetail jobDetail;

    @Builder
    public JobDetailPostingRelation(JobPosting jobPosting, JobDetail jobDetail) {
        this.jobPosting = jobPosting;
        this.jobDetail = jobDetail;
    }
}
