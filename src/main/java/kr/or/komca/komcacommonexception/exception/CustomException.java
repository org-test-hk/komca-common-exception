package kr.or.komca.komcacommonexception.exception;

import kr.or.komca.komcacommonexception.code.CommonErrorCode;
import kr.or.komca.komcacommonexception.code.ErrorCode;
import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
    }
}
