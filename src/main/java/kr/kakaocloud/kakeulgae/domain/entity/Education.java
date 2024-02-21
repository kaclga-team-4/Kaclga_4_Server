package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name="EDUCATION",
        uniqueConstraints = @UniqueConstraint(name= "education_uk", columnNames="type"),
        indexes = @Index(name = "education_idx_type", columnList = "type")
)
public class Education {
    @Id
    private Long id;

    @NotNull
    private String type;

    public Education(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public Education() {

    }
}
