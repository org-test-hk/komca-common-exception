package kr.or.komca.komcacommonexception.dto;

import kr.or.komca.komcadatacore.dto.common.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// 성공 응답용 Response
@Getter
public class SuccessResponse<T> extends BaseResponse<T> {
    @Builder
    private SuccessResponse(int status, T data) {
        super(status, null, data, null);
    }

    public static <T> ResponseEntity<BaseResponse<T>> ok(T data) {
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), data));
    }

    public static <T> ResponseEntity<BaseResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<>(HttpStatus.CREATED.value(), data));
    }
}