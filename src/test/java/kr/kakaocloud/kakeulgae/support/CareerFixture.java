package kr.kakaocloud.kakeulgae.support;

import kr.kakaocloud.kakeulgae.domain.entity.Career;

public class CareerFixture {

    final static Long CAREER_ID = 1L;
    final static String CAREER_TYPE = "신입";

    public static Career createCareer() {
        return new Career(CAREER_ID, CAREER_TYPE);
    }
}
