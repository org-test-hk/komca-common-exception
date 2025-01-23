package kr.or.komca.komcacommonexception.dto;


import kr.or.komca.komcacommonexception.response_code.SuccessCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// 성공 응답용 Response
@Getter
public class SuccessResponse<T> extends BaseResponse<T> {
    @Builder
    private SuccessResponse(SuccessCode successCode, T data) {
        super(successCode, data, null);
    }

    public static <T> ResponseEntity<BaseResponse<T>> ok(SuccessCode successCode,T data) {
        return ResponseEntity.ok(new SuccessResponse<>(successCode, data));
    }

    public static <T> ResponseEntity<BaseResponse<T>> created(SuccessCode successCode, T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>(successCode, data));
    }
    public static <T> ResponseEntity<BaseResponse<T>> of(SuccessCode successCode, T data) {
        return ResponseEntity.status(successCode.getStatus())
                .body(new SuccessResponse<>(successCode, data));
    }
}