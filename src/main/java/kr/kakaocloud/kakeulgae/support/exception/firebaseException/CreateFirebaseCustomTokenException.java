package kr.kakaocloud.kakeulgae.support.exception.firebaseException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class CreateFirebaseCustomTokenException extends FirebaseAuthException {

    public CreateFirebaseCustomTokenException(String message) {
        super(ErrorCode.FIREBASE_CUSTOM_TOKEN_CREATION_FAIL, message);
    }

}
