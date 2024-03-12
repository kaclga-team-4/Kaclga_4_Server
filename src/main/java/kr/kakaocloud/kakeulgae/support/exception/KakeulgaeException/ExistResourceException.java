package kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class ExistResourceException extends KakeulgaeException { //이미 존재하는 리소스 예외

    public ExistResourceException(String message) {
        super(ErrorCode.EXIST_RESOURCE, message);
    }

}
