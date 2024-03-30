package kr.kakaocloud.kakeulgae.support;

import kr.kakaocloud.kakeulgae.domain.entity.Education;

public class EducationFixture {

    final static Long EDUCATION_ID = 1L;
    final static String EDUCATION_TYPE = "educationName";

    public static Education createEducation() {
        return new Education(EDUCATION_ID, EDUCATION_TYPE);
    }
}
