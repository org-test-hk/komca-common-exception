package kr.or.komca.komcacommonexception.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode.getCode();
    }
}