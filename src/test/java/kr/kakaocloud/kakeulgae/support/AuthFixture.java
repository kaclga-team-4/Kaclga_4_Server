package kr.kakaocloud.kakeulgae.support;

public class AuthFixture {

    final static String TEST_ID_TOKEN = "testIdToken";
    public final static String TEST_BEARER_ID_TOKEN = STR."Bearer \{TEST_ID_TOKEN}";
    final static String TEST_NOT_EXIST_USER_ID_TOKEN = "testNotExistUserToken";
    final static String TEST_INVALID_ID_TOKEN = "testInvalidIdToken";
    final static String TEST_BEARER_NOT_EXIST_USER_ID_TOKEN = STR."Bearer \{TEST_NOT_EXIST_USER_ID_TOKEN}";
    final static String TEST_BEARER_INVALID_ID_TOKEN = STR."Bearer \{TEST_INVALID_ID_TOKEN}";
}
