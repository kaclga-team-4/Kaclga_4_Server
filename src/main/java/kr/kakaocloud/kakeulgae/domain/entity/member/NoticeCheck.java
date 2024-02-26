package kr.kakaocloud.kakeulgae.domain.entity.member;

public enum NoticeCheck {
    UNCHECKED("알림미설정"), CHECKED("알림설정");

    final String description;

    NoticeCheck(String description) {
        this.description = description;
    }
}
