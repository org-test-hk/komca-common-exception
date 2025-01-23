package kr.or.komca.komcacommonexception.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import kr.or.komca.komcacommonexception.response_code.BaseResponseCode;
import lombok.Getter;


//@Getter
//public abstract class BaseResponse {
////    protected final int status;
//    protected final Map<String,String> errors;
//    protected final ErrorCode errorCode;//구체화 되지 않은 응답코드
//
//    protected BaseResponse(final int status, final Map<String, String> errors) {
//        this.status = status;
//        this.errors = errors;
//    }
//}

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponse<T> {
//    protected final int status;
//    protected final String code;
    protected BaseResponseCode baseResponseCode;
    protected final T data;
    protected final Object errorDetail;

    protected BaseResponse(BaseResponseCode baseResponseCode, T data, Object errorDetail) {
        this.baseResponseCode = baseResponseCode;
        this.data = data;
        this.errorDetail = errorDetail;
    }
}