package kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class ObjectStorageException extends KakeulgaeException {

    public ObjectStorageException(String message) {
        super(ErrorCode.OBJECT_STORAGE_EXCEPTION, message);
    }
}
