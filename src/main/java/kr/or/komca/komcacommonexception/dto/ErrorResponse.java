package kr.or.komca.komcacommonexception.dto;

import kr.or.komca.komcadatacore.dto.common.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse extends BaseResponse {
    @Builder
    protected ErrorResponse(int status, Map<String, String> errors) {
        super(status, errors);
    }
    public static ErrorResponse of(HttpStatus status, Map<String, String> errors) {
        return ErrorResponse.builder()
                .status(status.value())
                .errors(errors)
                .build();
    }
    public static ErrorResponse of(HttpStatus status, String errorCode) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", errorCode);
        return of(status, errors);
    }
}

