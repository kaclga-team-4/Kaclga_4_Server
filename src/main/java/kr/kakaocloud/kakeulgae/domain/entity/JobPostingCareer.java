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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
    name = "job_posting_career"
)
public class JobPostingCareer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id",
        foreignKey = @ForeignKey(name = "job_posting_career_job_posting_id"))
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id",
        foreignKey = @ForeignKey(name = "job_posting_career_career_id"))
    private Career career;
}
