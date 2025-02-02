package kr.or.komca.komcacommonexception.exception;


import kr.or.komca.komcacommoninterface.exception.BaseException;
import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public abstract class CustomException extends RuntimeException implements BaseException {
    private final ErrorCode errorCode;
    private final Map<String, Object> params;  // 추가 파라미터를 위한 필드

    // 기본 생성자
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
        this.params = null;
    }

    // 추가 파라미터를 받는 생성자
    public CustomException(ErrorCode errorCode, Map<String, Object> params) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
        this.params = params;
    }
}
