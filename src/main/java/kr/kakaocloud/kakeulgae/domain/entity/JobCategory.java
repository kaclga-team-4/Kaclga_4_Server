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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
    name = "job_category",
    uniqueConstraints = @UniqueConstraint(name = "job_category_uk", columnNames = "type"),
    indexes = @Index(name = "job_category_idx_type", columnList = "type")
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String type;

    @OneToMany(mappedBy = "jobCategory")
    private List<JobDetail> jobDetails = new ArrayList<>();

}
