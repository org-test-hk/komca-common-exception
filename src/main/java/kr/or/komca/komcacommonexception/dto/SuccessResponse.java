package kr.or.komca.komcacommonexception.dto;

import kr.or.komca.komcacommoninterface.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SuccessResponse<T> extends BaseResponse {
    private final T data;

    private SuccessResponse(HttpStatus status, T data) {
        super(status.value());
        this.data = data;
    }

    public static <T> ResponseEntity<SuccessResponse<T>> ok(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SuccessResponse<>(HttpStatus.OK, data));
    }

    public static <T> ResponseEntity<SuccessResponse<T>> created(T data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse<>(HttpStatus.CREATED, data));
    }

    public static <T> ResponseEntity<SuccessResponse<T>> of(HttpStatus status, T data) {
        return ResponseEntity
                .status(status)
                .body(new SuccessResponse<>(status, data));
    }
}