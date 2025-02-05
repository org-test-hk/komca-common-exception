package kr.or.komca.komcacommonexception.handler;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import kr.or.komca.komcacommonexception.dto.CommonErrorResponse;
import kr.or.komca.komcacommonexception.exception.CustomException;
import kr.or.komca.komcacommonexception.response_code.CommonErrorCode;
import kr.or.komca.komcacommoninterface.dto.BaseResponse;
import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE) // 가장 낮은 우선순위
public class BaseGlobalExceptionHandler {


    @ExceptionHandler({CustomException.class})
    public ResponseEntity<BaseResponse> handleCustomException(CustomException e) {
        log.error("Custom error: {} | {}", e.getErrorCode().getCode(), e);
        // 기본 에러 정보만 전달
        CommonErrorResponse.ErrorDetail errorDetail = CommonErrorResponse.ErrorDetail.builder()
                .code(e.getErrorCode().getCode())
                .build();

        return CommonErrorResponse.of(e.getErrorCode(), errorDetail);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<BaseResponse> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        List<CommonErrorResponse.ErrorDetail> errorDetails = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    // 제약조건 관련 파라미터가 있는 경우 처리
                    return CommonErrorResponse.ErrorDetail.builder()
                            .field(error.getField())
                            .code(error.getDefaultMessage())
                            .value(error.getRejectedValue())
                            .build();
                })
                .collect(Collectors.toList());

        log.error("Validation error: {}", errorDetails);
        return CommonErrorResponse.of(CommonErrorCode.BAD_REQUEST, errorDetails);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {

        ErrorCode errorCode = CommonErrorCode.INVALID_FORMAT;

        // LocalDate 파싱 에러인 경우 더 구체적인 메시지 제공
        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) e.getCause();
            if (ife.getTargetType() == LocalDate.class) {
                errorCode = CommonErrorCode.INVALID_DATE_FORMAT;
            }
        }

        return CommonErrorResponse.from(errorCode);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleLowestException(Exception e) {
        log.error("Unexpected error occurred: {} | {}", CommonErrorCode.INTERNAL_SERVER_ERROR, e);

        return CommonErrorResponse.from(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }
}
