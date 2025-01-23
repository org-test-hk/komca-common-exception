package kr.or.komca.komcacommonexception.code;


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
