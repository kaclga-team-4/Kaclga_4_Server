package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(
        name="JOB",
        uniqueConstraints = @UniqueConstraint(name= "job_uk", columnNames="type"),
        indexes = @Index(name = "job_idx_type", columnList = "type")
)
public class Job {
    @Id
    private Long id;

    @NotNull
    private String type;

    public Job(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public Job() {

    }
}
