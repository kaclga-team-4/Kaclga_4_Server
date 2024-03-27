package kr.kakaocloud.kakeulgae;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.QJobPosting;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuerydslTest {

    @Autowired
    EntityManager em;

    @Autowired
    JobPostingRepository jobPostingRepository;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void querdslTest() throws Exception {
        // given
        QJobPosting jobPosting = QJobPosting.jobPosting;

        // when
        List<JobPosting> result = queryFactory
            .select(jobPosting)
            .from(jobPosting)
            .fetch();
        // then
        System.out.println("result = " + result);
        for (JobPosting posting : result) {
            System.out.println("posting = " + posting);
        }
    }
}
