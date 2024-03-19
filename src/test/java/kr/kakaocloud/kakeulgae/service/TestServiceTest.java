package kr.kakaocloud.kakeulgae.service;

import static kr.kakaocloud.kakeulgae.support.TestFixtures.createTestPostRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import kr.kakaocloud.kakeulgae.domain.TestEntity;
import kr.kakaocloud.kakeulgae.repository.TestRepository;
import kr.kakaocloud.kakeulgae.service.dto.TestPostRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        //given
        testService.createTest(testPostRequest);
        TestEntity test = TestEntity.builder().name(testPostRequest.getName()).build();
        given(testRepository.save(test)).willReturn(test);

        // when
        testService.createTest(testPostRequest);

        // then
        verify(testRepository).save(any());
    }

}
