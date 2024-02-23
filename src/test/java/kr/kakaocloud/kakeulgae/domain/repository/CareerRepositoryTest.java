package kr.kakaocloud.kakeulgae.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CareerRepositoryTest {

    @Autowired
    private CareerRepository repository;

    @Test
    public void 조회() throws Exception {
        repository.findAll();
    }
}