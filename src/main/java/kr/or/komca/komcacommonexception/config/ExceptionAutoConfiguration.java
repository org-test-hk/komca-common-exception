package kr.or.komca.komcacommonexception.config;

import kr.or.komca.komcacommonexception.handler.BaseGlobalExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ExceptionAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public BaseGlobalExceptionHandler globalExceptionHandler() {
        return new BaseGlobalExceptionHandler();
    }
}
