package kr.kakaocloud.kakeulgae.domain.dto;

import jakarta.validation.constraints.NotBlank;
import kr.kakaocloud.kakeulgae.domain.entity.TestEntity;
import lombok.*;

public class TestDtos {
    @Getter
    @Setter
    @AllArgsConstructor
    //getter, setter, hashcode, toString, equals 자동 재정의
    public static class TestPostRequest {
        @NotBlank
        private String name;
    }

    @Data
//getter, setter, hashcode, toString, equals 자동 재정의
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestResponse {
        private String name;

        public static TestResponse of(TestEntity t) {
            return TestResponse.builder().name(t.getName()).build();
        }
    }
}
