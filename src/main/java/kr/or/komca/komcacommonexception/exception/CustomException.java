//package kr.or.komca.komcacommonexception.exception;
//
//
//import kr.or.komca.komcacommoninterface.exception.BaseException;
//import kr.or.komca.komcacommoninterface.response_code.ErrorCode;
//import lombok.Getter;
//
//import java.util.Map;
//import java.util.Objects;
//
//@Getter
//public abstract class CustomException extends RuntimeException implements BaseException {
//    private final ErrorCode errorCode;
//    private final Object value;
//    private final String field;
//
//    // 기본 생성자
//    public CustomException(ErrorCode errorCode) {
//        super(errorCode.getCode());
//        this.errorCode = errorCode;
//        this.value = null;
//        this.field = null;
//    }
//
//    // 추가 파라미터를 받는 생성자
//    public CustomException(ErrorCode errorCode, String field, Object value) {
//        super(errorCode.getCode());
//        this.errorCode = errorCode;
//        this.value = value;
//        this.field = field;
//    }
//}
