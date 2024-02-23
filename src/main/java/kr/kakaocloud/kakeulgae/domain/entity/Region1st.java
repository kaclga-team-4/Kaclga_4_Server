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
    name = "region_1st",
    uniqueConstraints = @UniqueConstraint(name = "region_1st_uk", columnNames = "type"),
    indexes = @Index(name = "region_1st_idx_type", columnList = "type")
)
public class Region1st {

    @Id
    @Column(name = "region1st_id")
    private Long id;

    @NotEmpty
    private String type;

    public Region1st(long l, String splt) {
        this.id = l;
        this.type = splt;
    }

    public Region1st() {

    }
}
