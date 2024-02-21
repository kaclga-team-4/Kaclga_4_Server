package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        name="REGION_1ST",
        uniqueConstraints = @UniqueConstraint(name= "region_1st_uk", columnNames="type"),
        indexes = @Index(name = "region_1st_idx_type", columnList = "type")
)
public class Region1st {
    @Id
    private Long id;

    @NotNull
    private String type;

    public Region1st(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public Region1st() {

    }
}
