package kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException;

import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class KakeulgaeException extends RuntimeException {

    ErrorCode errorCode;
    @Nullable
    String message;

    public KakeulgaeException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
