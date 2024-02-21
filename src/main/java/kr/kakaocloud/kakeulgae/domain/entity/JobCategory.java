package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "JOB_CATEGORY",
    uniqueConstraints = @UniqueConstraint(name = "job_category_uk", columnNames = "type"),
    indexes = @Index(name = "job_category_idx_type", columnList = "type")
)
public class JobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;

    public JobCategory(String content) {
        this.type = content;
    }

    public JobCategory() {

    }
}
