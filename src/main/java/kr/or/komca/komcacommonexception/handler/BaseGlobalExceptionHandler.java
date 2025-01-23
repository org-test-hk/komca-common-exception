package kr.or.komca.komcacommonexception.handler;

import kr.or.komca.komcacommonexception.code.CommonErrorCode;
import kr.or.komca.komcacommonexception.dto.ErrorResponse;
import kr.or.komca.komcacommonexception.exception.CustomException;
import kr.or.komca.komcadatacore.dto.common.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class BaseGlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<Void>> handleCustomException(CustomException ex) {
        log.error("Custom error: {}", ex.getErrorCode().getCode());
        return ErrorResponse.of(ex.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing
                ));

        log.error("Validation error: {}", errors);
        return ErrorResponse.of(CommonErrorCode.BAD_REQUEST, errors);
    }


}