package kr.or.komca.komcacommonexception.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.komca.komcacommonexception.response_code.BaseResponseCode;
import kr.or.komca.komcacommonexception.response_code.ErrorCode;
import kr.or.komca.komcacommonexception.response_code.SuccessCode;
import lombok.Getter;

import java.time.LocalDateTime;


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
    @JsonProperty("errorCode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected ErrorCode errorCode;

    @JsonProperty("successCode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected SuccessCode successCode;

    protected final T data;
    protected final Object errorDetail;
    protected final LocalDateTime timestamp = LocalDateTime.now();

    protected BaseResponse(BaseResponseCode baseResponseCode, T data, Object errorDetail) {
        if (baseResponseCode instanceof ErrorCode) {
            this.errorCode = (ErrorCode) baseResponseCode;
        } else if (baseResponseCode instanceof SuccessCode) {
            this.successCode = (SuccessCode) baseResponseCode;
        }
        this.data = data;
        this.errorDetail = errorDetail;
    }
}