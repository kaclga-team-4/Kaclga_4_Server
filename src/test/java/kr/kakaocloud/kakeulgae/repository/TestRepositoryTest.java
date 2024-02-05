package kr.kakaocloud.kakeulgae.repository;

import static kr.kakaocloud.kakeulgae.TestFeature.TEST_NAME;
import static org.junit.jupiter.api.Assertions.*;

import kr.kakaocloud.kakeulgae.domain.entity.TestEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TestRepositoryTest {

    @Autowired
    TestRepository testRepository;

    @Test
    @DisplayName("TestEntity 생성")
    void createMember() {
        TestEntity t1 = TestEntity.builder().name(TEST_NAME).build();
    /*
    when
     */
        TestEntity result1 = testRepository.save(t1);

    /*
    then
     */
        assert (result1.getName()).equals(t1.getName());
    }
}