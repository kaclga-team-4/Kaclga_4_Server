package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Table(
    name = "region_2nd"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region2nd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_1st_id", foreignKey = @ForeignKey(name = "fk_region_2nd"))
    private Region1st region1st;

    public Region2nd(long l, String splt, Region1st region1st) {
        this.id = l;
        this.type = splt;
        this.region1st = region1st;
    }
}
