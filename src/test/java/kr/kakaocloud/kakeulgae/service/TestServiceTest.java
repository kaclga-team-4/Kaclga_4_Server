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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TestServiceTest {

    @Autowired
    private TestService testService;
    @MockBean
    private TestRepository testRepository;

    @Test
    void createTest() {
        TestPostRequest testPostRequest = createTestPostRequest();
        TestEntity test = TestEntity.builder().name(testPostRequest.getName()).build();
        //given
        given(testRepository.save(any())).willReturn(test);

        // when
        testService.createTest(testPostRequest);

        // then
        verify(testRepository).save(any());
    }

}
