package kr.kakaocloud.kakeulgae.support.exception.firebaseException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class WithdrawFirebaseUserException extends FirebaseAuthException {

    public WithdrawFirebaseUserException(String message) {
        super(ErrorCode.FIREBASE_USER_WITHDRAW_FAIL, message);
    }

}
