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

@Entity
@Table(
    name = "job",
    uniqueConstraints = @UniqueConstraint(name = "job_uk", columnNames = "type"),
    indexes = @Index(name = "job_idx_type", columnList = "type")
)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String type;

    public Job(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public Job() {

    }
}
