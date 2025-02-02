package kr.or.komca.komcacommonexception.dto;


import kr.or.komca.komcacommoninterface.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// 성공 응답용 Response
//@Getter
//public class SuccessResponse<T> extends BaseResponse<T> {
//    @Builder
//    private SuccessResponse(SuccessCode successCode, T data) {
//        super(successCode, data, null);
//    }
//
//    public static <T> ResponseEntity<BaseResponse<T>> ok(SuccessCode successCode,T data) {
//        return ResponseEntity.ok(new SuccessResponse<>(successCode, data));
//    }
//
//    public static <T> ResponseEntity<BaseResponse<T>> created(SuccessCode successCode, T data) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(new SuccessResponse<>(successCode, data));
//    }
//    public static <T> ResponseEntity<BaseResponse<T>> of(SuccessCode successCode, T data) {
//        return ResponseEntity.status(successCode.getStatus())
//                .body(new SuccessResponse<>(successCode, data));
//    }
//}

@Getter
public class SuccessResponse<T> extends BaseResponse<T> {
    private SuccessResponse(T data) {
        super(HttpStatus.OK.value(), data);
    }

    private SuccessResponse(HttpStatus status, T data) {
        super(status.value(), data);
    }

    public static <T> ResponseEntity<BaseResponse<T>> ok(T data) {
        return ResponseEntity.ok(new SuccessResponse<>(data));
    }

    public static <T> ResponseEntity<BaseResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>(HttpStatus.CREATED, data));
    }
}