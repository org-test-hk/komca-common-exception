package kr.or.komca.komcacommonexception.config;

import kr.or.komca.komcacommonexception.handler.BaseGlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionAutoConfiguration {

//    @Bean
//    public BaseGlobalExceptionHandler BaseGlobalExceptionHandler() {
//        return new BaseGlobalExceptionHandler();
//    }
}
