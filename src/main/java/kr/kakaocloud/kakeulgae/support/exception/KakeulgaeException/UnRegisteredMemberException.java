package kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class UnRegisteredMemberException extends KakeulgaeException {

    public UnRegisteredMemberException(String message) {
        super(ErrorCode.UNREGISTERED_MEMBER, message);//등록되지 않은 회원 예외
    }

}
