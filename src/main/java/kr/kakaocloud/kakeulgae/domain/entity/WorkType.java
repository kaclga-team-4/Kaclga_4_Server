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
@Getter
@Table(
    name = "work_type",
    uniqueConstraints = @UniqueConstraint(name = "worktype_uk", columnNames = "type"),
    indexes = @Index(name = "idx_type", columnList = "type")
)
public class WorkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String type;

    public WorkType(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public WorkType() {

    }
}
