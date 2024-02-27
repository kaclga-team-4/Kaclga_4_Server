package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "post_name")
    private String postName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "fk_job_posting_job_id"))
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id", foreignKey = @ForeignKey(name = "fk_job_posting_education_id"))
    private Education education;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", foreignKey = @ForeignKey(name = "fk_job_posting_career_id"))
    private Career career;

    private String url;

    private LocalDate deadline;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
