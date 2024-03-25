package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
@Entity
@Table(
    name = "job_category",
    uniqueConstraints = @UniqueConstraint(name = "job_category_uk", columnNames = "type"),
    indexes = @Index(name = "job_category_idx_type", columnList = "type")
)
public class JobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty
    private String type;

    public JobCategory(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public JobCategory() {

    }
}
