package kr.or.komca.komcacommonexception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.or.komca.komcacommoninterface.dto.BaseResponse;
import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Getter
public class CommonErrorResponse extends BaseResponse {
    private final List<ErrorDetail> errorDetails;
    private final String code;

    private CommonErrorResponse(ErrorCode errorCode, List<ErrorDetail> errorDetails) {
        super(errorCode.getStatusCode());
        this.errorDetails = errorDetails;
        this.code = errorCode.getCode();
    }

    public static ResponseEntity<BaseResponse> from(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new CommonErrorResponse(errorCode, null));
    }

    public static ResponseEntity<BaseResponse> of(ErrorCode errorCode, List<ErrorDetail> errorDetails) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new CommonErrorResponse(errorCode, errorDetails));
    }

    public static ResponseEntity<BaseResponse> of(ErrorCode errorCode, ErrorDetail errorDetails) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new CommonErrorResponse(errorCode, List.of(errorDetails)));
    }

    public static ResponseEntity<BaseResponse> of(ErrorCode errorCode, String field, String code, String message) {
        ErrorDetail errorDetail = ErrorDetail.builder()
                .field(field)
                .code(code)
                .message(message)
                .build();

        return of(errorCode, List.of(errorDetail));
    }


    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ErrorDetail {
        private final String field;        // 에러 발생 필드
        private final String code;         // 에러 상세 코드
        private final Object value;        // 실제 입력값 (옵션)
        private final String message;
    }
}

