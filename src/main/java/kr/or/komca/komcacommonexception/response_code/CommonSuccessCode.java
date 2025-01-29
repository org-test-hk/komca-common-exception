package kr.or.komca.komcacommonexception.response_code;


import kr.or.komca.komcacommoninterface.response_code.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonSuccessCode implements SuccessCode {
    OK_200("SUCCESS_200", HttpStatus.OK),
    CREATED_201("SUCCESS_201", HttpStatus.CREATED);

    private final String code;
    private final HttpStatus status;
}
