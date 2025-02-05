package kr.or.komca.komcacommonexception.response_code;

import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    BAD_REQUEST("BAD_REQUEST", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("UNAUTHORIZED", HttpStatus.UNAUTHORIZED),
    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_FORMAT("INVALID_FORMAT_ERROR", HttpStatus.BAD_REQUEST),
    INVALID_DATE_FORMAT("INVALID_DATE_FORMAT_ERROR", HttpStatus.BAD_REQUEST),;

    private final String code;
    private final HttpStatus status;
}
