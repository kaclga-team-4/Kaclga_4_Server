package kr.kakaocloud.kakeulgae.domain.entity.member;

public enum MemberRole {
    ROLE_USER("사용자"), ROLE_ADMIN("관리자");
    private String description;

    MemberRole(String description) {
        this.description = description;
    }
}
