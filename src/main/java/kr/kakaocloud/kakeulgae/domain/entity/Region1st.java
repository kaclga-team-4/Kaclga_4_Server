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

@Getter
@Entity
@Table(
    name = "region_1st",
    uniqueConstraints = @UniqueConstraint(name = "region_1st_uk", columnNames = "type"),
    indexes = @Index(name = "region_1st_idx_type", columnList = "type")
)
public class Region1st {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String type;

    @OneToMany(mappedBy = "region1st")
    private List<Region2nd> region2nds = new ArrayList<>();
}
