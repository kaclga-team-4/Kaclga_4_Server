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
    name = "career",
    uniqueConstraints = @UniqueConstraint(name = "career_uk", columnNames = "type"),
    indexes = @Index(name = "career_idx_type", columnList = "type")
)
public class Career {

    @Id
    private Long id;
    @NotEmpty
    private String type;

}
