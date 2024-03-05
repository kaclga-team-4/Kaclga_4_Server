package kr.kakaocloud.kakeulgae.domain.entity.member;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    M("남자"), F("여자");

    final String description;

    Gender(String description) {
        this.description = description;
    }

    @JsonCreator
    public static Gender fromString(String value) {
        return valueOf(value.toUpperCase());
    }
}
