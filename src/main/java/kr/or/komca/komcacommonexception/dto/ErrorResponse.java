package kr.or.komca.komcacommonexception.dto;

import kr.or.komca.komcacommoninterface.dto.BaseResponse;
import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

//@Getter
//public class ErrorResponse extends BaseResponse {
//    @Builder
//    protected ErrorResponse(int status, Map<String, String> errors) {
//        super(status, errors);
//    }
//    public static ErrorResponse of(HttpStatus status, Map<String, String> errors) {
//        return ErrorResponse.builder()
//                .status(status.value())
//                .errors(errors)
//                .build();
//    }
//    public static ErrorResponse of(HttpStatus status, String errorCode) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("error", errorCode);
//        return of(status, errors);
//    }
//}

@Getter
public class ErrorResponse extends BaseResponse<Void> {
    private ErrorResponse(ErrorCode errorCode, Object errorDetail) {
        super(errorCode, null, errorDetail);
    }

    public static ResponseEntity<BaseResponse<Void>> of(ErrorCode errorCode) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", errorCode.getCode());
        return ResponseEntity.status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode, errors));
    }

    public static ResponseEntity<BaseResponse<Void>> of(ErrorCode errorCode, Object errorDetail) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode, errorDetail));
    }
}

