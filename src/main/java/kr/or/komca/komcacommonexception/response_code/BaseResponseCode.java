package kr.or.komca.komcacommonexception.response_code;

import org.springframework.http.HttpStatus;

public interface BaseResponseCode {
    String getCode();
    HttpStatus getStatus();
}
