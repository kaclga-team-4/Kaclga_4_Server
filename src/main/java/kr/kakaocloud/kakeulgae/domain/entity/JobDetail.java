package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "JOB_DETAIL",
    uniqueConstraints = @UniqueConstraint(name = "job_detail_uk", columnNames = {"type", "job_id"}),
    indexes = @Index(name = "job_detail_idx_type", columnList = "type")
)
public class JobDetail {

    @Id
    private Long id;

    @NotNull
    private String type;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;

    public JobDetail(long l, String splt, Job job) {
        this.id = l;
        this.type = splt;
        this.job = job;
    }

    public JobDetail() {

    }
}
