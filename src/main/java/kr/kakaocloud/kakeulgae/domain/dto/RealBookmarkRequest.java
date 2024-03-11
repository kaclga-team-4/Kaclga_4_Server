package kr.kakaocloud.kakeulgae.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealBookmarkRequest {

    private Long memberId;
    private Long jobPostingId;
}
