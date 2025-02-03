package kr.or.komca.komcacommonexception.handler;


import kr.or.komca.komcacommonexception.dto.CommonErrorResponse;
import kr.or.komca.komcacommonexception.exception.CustomException;
import kr.or.komca.komcacommonexception.response_code.CommonErrorCode;
import kr.or.komca.komcacommoninterface.dto.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE) // 가장 낮은 우선순위
public class BaseGlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse> handleCustomException(CustomException ex) {
        log.error("Custom error: {}", ex.getErrorCode().getCode());
        // 기본 에러 정보만 전달
        CommonErrorResponse.ErrorDetail errorDetail = CommonErrorResponse.ErrorDetail.builder()
                .code(ex.getErrorCode().getCode())
                .build();

        return CommonErrorResponse.of(ex.getErrorCode(), List.of(errorDetail));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<CommonErrorResponse.ErrorDetail> errorDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, Object> params = new HashMap<>();
                    // 제약조건 관련 파라미터가 있는 경우 처리
                    if (error.getArguments() != null && error.getArguments().length > 1) {
                        params.put("rejectedValue", error.getRejectedValue());
                    }

                    return CommonErrorResponse.ErrorDetail.builder()
                            .field(error.getField())
                            .code(error.getDefaultMessage())
                            .value(error.getRejectedValue())
                            .params(!params.isEmpty() ? params : null)
                            .build();
                })
                .collect(Collectors.toList());

        log.error("Validation error: {}", errorDetails);
        return CommonErrorResponse.of(CommonErrorCode.BAD_REQUEST, errorDetails);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<BaseResponse> handleException(Exception ex) {
//        log.error("Unexpected error occurred: ", ex);
//
//        CommonErrorResponse.ErrorDetail errorDetail = CommonErrorResponse.ErrorDetail.builder()
//                .code("INTERNAL_SERVER_ERROR")
//                .build();
//
//        return CommonErrorResponse.of(CommonErrorCode.INTERNAL_SERVER_ERROR, List.of(errorDetail));
//    }

}
