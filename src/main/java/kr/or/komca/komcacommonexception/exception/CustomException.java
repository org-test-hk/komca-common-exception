package kr.or.komca.komcacommonexception.exception;

import kr.or.komca.komcacommonexception.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String errorCode;

    public CustomException(ErrorCode error) {
        super(error.getCode());
        this.status = error.getStatus();
        this.errorCode = error.getCode();
    }
}
