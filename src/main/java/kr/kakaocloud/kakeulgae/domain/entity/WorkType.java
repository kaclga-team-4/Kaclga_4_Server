package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name="WORK_TYPE",
        uniqueConstraints = @UniqueConstraint(name= "workType_uk", columnNames="type"),
        indexes = @Index(name = "idx_type", columnList = "type")
)
public class WorkType {
    @Id
    private Long id;

    @NotNull
    private String type;

    public WorkType(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public WorkType() {

    }
}
