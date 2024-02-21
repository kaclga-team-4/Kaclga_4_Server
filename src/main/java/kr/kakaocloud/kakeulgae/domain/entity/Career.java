package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "CAREER",
    uniqueConstraints = @UniqueConstraint(name = "career_uk", columnNames = "type"),
    indexes = @Index(name = "career_idx_type", columnList = "type")
)
public class Career {

    @Id
    private Long id;
    @NotNull
    private String type;

}
