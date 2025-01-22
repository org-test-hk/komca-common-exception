package kr.or.komca.komcacommonexception.handler;

import kr.or.komca.komcacommonexception.dto.ErrorResponse;
import kr.or.komca.komcacommonexception.exception.CustomException;
import kr.or.komca.komcadatacore.dto.common.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<? extends BaseResponse> handleCustomException(CustomException ex) {
        log.error("Custom error: {}", ex.getErrorCode());
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getErrorCode());

        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getStatus().value())
                .errors(errors)
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<? extends BaseResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing  // 중복 키 처리
                ));

        log.error("Validation error: {}", errors);
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(HttpStatus.BAD_REQUEST, errors));
    }


}
