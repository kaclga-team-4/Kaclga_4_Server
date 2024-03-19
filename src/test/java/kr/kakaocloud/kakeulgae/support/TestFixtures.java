package kr.kakaocloud.kakeulgae.support;

import kr.kakaocloud.kakeulgae.service.dto.TestPostRequest;
import kr.kakaocloud.kakeulgae.service.dto.TestResponse;

public class TestFixtures {

    public static final String TEST_NAME = "testName";
 
    public static TestPostRequest createTestPostRequest() {
        return new TestPostRequest(TEST_NAME, null);
    }

    public static TestResponse createTestResponse() {
        return new TestResponse(TEST_NAME);
    }
}
