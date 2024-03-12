package kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException;

import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;

public class S3Exception extends KakeulgaeException {

    public S3Exception(String message) {
        super(ErrorCode.S3_EXCEPTION, message);
    }
}
