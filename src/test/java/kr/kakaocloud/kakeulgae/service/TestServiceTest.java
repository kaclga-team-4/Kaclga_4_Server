package kr.kakaocloud.kakeulgae.service;

import static kr.kakaocloud.kakeulgae.support.TestFixtures.createTestPostRequest;

import kr.kakaocloud.kakeulgae.domain.dto.TestDtos.TestPostRequest;
import kr.kakaocloud.kakeulgae.domain.entity.TestEntity;
import kr.kakaocloud.kakeulgae.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Autowired
    private TestService testService;
    @Mock
    private TestRepository testRepository;

    @Test
    void createTest() {

        TestPostRequest testPostRequest = createTestPostRequest();
        //when
        testService.createTest(testPostRequest);
        TestEntity test = TestEntity.builder().name(testPostRequest.getName()).build();
        given(testRepository.save(test)).willReturn(test);

        // when
        testService.createTest(testPostRequest);

        // then
        verify(testRepository).save(any());
    }
    }

    @Test
    void getId() {
    }
}