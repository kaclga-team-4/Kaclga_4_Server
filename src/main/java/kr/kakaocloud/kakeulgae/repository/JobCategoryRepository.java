package kr.kakaocloud.kakeulgae.repository;


import kr.kakaocloud.kakeulgae.domain.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {

}
