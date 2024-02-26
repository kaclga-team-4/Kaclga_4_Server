package kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class NotFoundDefaultResourceException extends KakeulgaeException {

    public NotFoundDefaultResourceException(String message) {
        super(ErrorCode.NOT_FOUND_DEFAULT_RESOURCE, message);
    }
}
