package kr.kakaocloud.kakeulgae.config.handler;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;
import lombok.Data;

@Data
class UnAuthorizedResponse {
    int code = ErrorCode.UNAUTHORIZED.code;
    String errorMessage = ErrorCode.UNAUTHORIZED.errorMessage;
}
