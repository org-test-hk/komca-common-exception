package kr.or.komca.komcacommonexception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateResourceException extends BusinessException {
    public DuplicateResourceException() {
        super(ErrorCode.DUPLICATE);
    }
}