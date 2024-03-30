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
import lombok.Getter;

@Entity
@Table(
    name = "region_posting_relation",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "region_posting_relation_job_posting_id_region_2nd_id_unique",
            columnNames = {"job_posting_id", "region_2nd_id"}
        )
    }
)
@Getter
public class RegionPostingRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id",
        foreignKey = @ForeignKey(name = "fk_region_posting_relation_job_posting_id"))
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_2nd_id",
        foreignKey = @ForeignKey(name = "fk_region_posting_relation_region_2nd_id"))
    private Region2nd region2nd;
}
