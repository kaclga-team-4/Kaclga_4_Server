package kr.kakaocloud.kakeulgae.domain.entity.member;

public enum Gender {
    M("남자"), F("여자");

    final String description;

    Gender(String description) {
        this.description = description;
    }
}
