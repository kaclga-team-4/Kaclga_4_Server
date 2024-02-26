package kr.kakaocloud.kakeulgae.support.exception.firebaseException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class InvalidTokenException extends FirebaseAuthException {

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_FIREBASE_ID_TOKEN, message);
    }
}
