package kr.kakaocloud.kakeulgae.repository;

import static kr.kakaocloud.kakeulgae.support.TestFixtures.TEST_NAME;

import kr.kakaocloud.kakeulgae.domain.entity.TestEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    @DisplayName("TestEntity 생성")
    void createMember() {
        TestEntity t1 = TestEntity.builder()
            .name(TEST_NAME)
            .build();
    /*
    when
     */
        TestEntity result1 = testRepository.save(t1);
    /*
    then
     */
        Assertions.assertThat(result1).isSameAs(t1);
    }
}
