package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
    name = "region_1st",
    uniqueConstraints = @UniqueConstraint(name = "region_1st_uk", columnNames = "type"),
    indexes = @Index(name = "region_1st_idx_type", columnList = "type")
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region1st {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String type;

    public Region1st(long l, String splt) {
        this.id = l;
        this.type = splt;
    }
}
