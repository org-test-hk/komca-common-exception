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

    public static <T> SuccessResponse<T> ok(T data) {
        return new SuccessResponse<>(HttpStatus.OK, data);
    }

    public static <T> SuccessResponse<T> created(T data) {
        return new SuccessResponse<>(HttpStatus.CREATED, data);
    }

    public static <T> SuccessResponse<T> of(HttpStatus status, T data) {
        return new SuccessResponse<>(status, data);
    }

    @Override
    public ResponseEntity<SuccessResponse<T>> toResponseEntity() {
        return ResponseEntity.status(this.statusCode).body(this);
    }
}