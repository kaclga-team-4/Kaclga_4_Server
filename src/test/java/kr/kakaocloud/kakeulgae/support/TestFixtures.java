package kr.kakaocloud.kakeulgae.support;

import kr.kakaocloud.kakeulgae.domain.dto.TestDtos.TestPostRequest;
import kr.kakaocloud.kakeulgae.domain.dto.TestDtos.TestResponse;


public class TestFixtures {
    public static final String TEST_NAME = "testName";

    public static TestPostRequest createTestPostRequest() {
        return new TestPostRequest(TEST_NAME);
    }

    public static TestResponse createTestResponse() {
        return new TestResponse(TEST_NAME);
    }
}
