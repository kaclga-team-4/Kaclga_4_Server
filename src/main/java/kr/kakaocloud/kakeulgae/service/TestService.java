package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.domain.dto.TestPostRequest;
import kr.kakaocloud.kakeulgae.domain.dto.TestResponse;
import kr.kakaocloud.kakeulgae.domain.entity.TestEntity;
import kr.kakaocloud.kakeulgae.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void createTest(TestPostRequest t){
        TestEntity test = TestEntity.builder().name(t.getName()).build();
        testRepository.save(test);
    }
    public TestResponse getId(Long id) {
        TestEntity test = testRepository.findById(id).orElseThrow();
        return TestResponse.of(test);
    }
}
