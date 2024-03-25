package kr.kakaocloud.kakeulgae.service.dto;

import kr.kakaocloud.kakeulgae.domain.TestEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//getter, setter, hashcode, toString, equals 자동 재정의
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResponse {

    private String name;

    public static TestResponse of(TestEntity t) {
        return TestResponse.builder().name(t.getName()).build();
    }
}
