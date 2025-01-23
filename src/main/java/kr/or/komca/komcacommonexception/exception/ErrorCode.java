package kr.or.komca.komcacommonexception.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 비즈니스 오류 코드 정의
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common Resource Error Codes
    NOT_FOUND("NOT_FOUND"),
    DUPLICATE("DUPLICATE"),

    // Validation Error Codes
    TITLE_REQUIRED("TITLE_REQUIRED"),
    TITLE_TOO_LONG("TITLE_TOO_LONG"),
    CONTENT_TOO_LONG("CONTENT_TOO_LONG"),
    INVALID_DATE_FORMAT("INVALID_DATE_FORMAT"),

    // Business Error Codes
    UNAUTHORIZED("UNAUTHORIZED"),
    ACCESS_DENIED("ACCESS_DENIED"),
    INVALID_STATUS("INVALID_STATUS"),

    UPDATE_FAILED("UPDATE_FAILED"),
    DELETE_FAILED("DELETE_FAILED"),

    SIZE_REQUIRED("SIZE_REQUIRED");

    private final String code;
}
