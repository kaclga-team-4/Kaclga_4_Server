package kr.kakaocloud.kakeulgae.domain.dto;

import lombok.Data;

@Data
public class BookmarkRequest {
    private Long memberId;
    private Long jobPostingId;

    public BookmarkRequest() {}
}
