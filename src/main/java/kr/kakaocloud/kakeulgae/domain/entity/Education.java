package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(
    name = "education",
    uniqueConstraints = @UniqueConstraint(name = "education_uk", columnNames = "type"),
    indexes = @Index(name = "education_idx_type", columnList = "type")
)
public class Education {

    @Id
    private Long id;

    @NotEmpty
    private String type;

    public Education(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public Education() {

    }
}
