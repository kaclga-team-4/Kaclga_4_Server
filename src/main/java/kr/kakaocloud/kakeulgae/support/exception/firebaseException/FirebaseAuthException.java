package kr.kakaocloud.kakeulgae.support.exception.firebaseException;

import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class FirebaseAuthException extends RuntimeException {

    ErrorCode errorCode;
    @Nullable
    String message;

    public FirebaseAuthException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
