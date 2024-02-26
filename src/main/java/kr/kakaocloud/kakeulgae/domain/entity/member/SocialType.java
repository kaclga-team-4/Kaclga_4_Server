package kr.kakaocloud.kakeulgae.domain.entity.member;

public enum SocialType {
    GOOGLE("구글"), EMAIL("이메일");

    private String description;

    SocialType(String description) {
        this.description = description;
    }
}
