package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(
    name = "region_2nd",
    uniqueConstraints = @UniqueConstraint(name = "region_2st_uk", columnNames = "type"),
    indexes = @Index(name = "region_2st_idx_type", columnList = "type")
)
public class Region2nd {

    @Id
    private Long id;

    @NotEmpty
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_1st_id")
    private Region1st region1st;

    public Region2nd(long l, String splt, Region1st region1st) {
        this.id = l;
        this.type = splt;
        this.region1st = region1st;
    }

    public Region2nd() {

    }
}
