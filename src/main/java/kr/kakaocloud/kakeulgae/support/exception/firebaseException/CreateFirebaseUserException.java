package kr.kakaocloud.kakeulgae.support.exception.firebaseException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class CreateFirebaseUserException extends FirebaseAuthException {

    public CreateFirebaseUserException(String message) {
        super(ErrorCode.FIREBASE_USER_CREATION_FAIL, message);
    }

}
