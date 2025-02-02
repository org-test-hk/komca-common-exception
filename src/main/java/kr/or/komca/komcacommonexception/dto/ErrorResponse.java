package kr.or.komca.komcacommonexception.dto;

import kr.or.komca.komcacommoninterface.dto.BaseResponse;
import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
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


    private ErrorResponse(ErrorCode errorCode, List<ErrorDetail> errorDetails) {
        super(errorCode.getStatus().value(), errorCode.getCode(), errorDetails);
    }

    public static ResponseEntity<BaseResponse<Void>> of(ErrorCode errorCode, List<ErrorDetail> errorDetails) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new ErrorResponse(errorCode, errorDetails));
    }

    public static ResponseEntity<BaseResponse<Void>> of(ErrorCode errorCode, String field, String code) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .field(field)
                .code(code)
                .build();

        return of(errorCode, List.of(errorDetail));
    }

    public static ResponseEntity<BaseResponse<Void>> of(ErrorCode errorCode, String field, String code,
                                                        Map<String, Object> params) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .field(field)
                .code(code)
                .params(params)
                .build();

        return of(errorCode, List.of(errorDetail));
    }
}

