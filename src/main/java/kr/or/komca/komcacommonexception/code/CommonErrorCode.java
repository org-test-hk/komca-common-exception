package kr.or.komca.komcacommonexception.code;

import kr.or.komca.komcadatacore.code.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    BAD_REQUEST("ERROR_400", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("ERROR_401", HttpStatus.UNAUTHORIZED),
    NOT_FOUND("ERROR_404", HttpStatus.NOT_FOUND);

    private final String code;
    private final HttpStatus status;
}
