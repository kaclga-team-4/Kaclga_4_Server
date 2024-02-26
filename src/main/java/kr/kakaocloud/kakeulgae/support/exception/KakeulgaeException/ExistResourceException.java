package kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class ExistResourceException extends KakeulgaeException {

    public ExistResourceException(String message) {
        super(ErrorCode.EXIST_RESOURCE, message);
    }

}
