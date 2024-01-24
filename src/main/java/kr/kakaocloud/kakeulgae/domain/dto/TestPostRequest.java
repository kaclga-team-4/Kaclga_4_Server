package kr.kakaocloud.kakeulgae.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    //getter, setter, hashcode, toString, equals 자동 재정의
    @NoArgsConstructor
    @AllArgsConstructor
    public class TestPostRequest {
        private String name;
    }
