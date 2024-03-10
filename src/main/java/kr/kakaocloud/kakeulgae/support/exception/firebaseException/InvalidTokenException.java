package kr.kakaocloud.kakeulgae.support.exception.firebaseException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class InvalidTokenException extends FirebaseAuthException {

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_FIREBASE_ID_TOKEN, message);//firebase id token이 유효하지 않을 때 발생하는 예외
    }
}
