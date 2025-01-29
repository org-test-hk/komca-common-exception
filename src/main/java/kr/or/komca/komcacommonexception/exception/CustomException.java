package kr.or.komca.komcacommonexception.exception;


import kr.or.komca.komcacommoninterface.exception.BaseException;
import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException implements BaseException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
    }
}
