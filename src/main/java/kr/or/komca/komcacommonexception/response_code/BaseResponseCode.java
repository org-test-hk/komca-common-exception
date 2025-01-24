package kr.or.komca.komcacommonexception.response_code;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface BaseResponseCode {
    String getCode();

    @JsonIgnore
    HttpStatus getStatus();

    @JsonProperty("status")
    default int getStatusCode() {
        return getStatus().value();
    }
}
