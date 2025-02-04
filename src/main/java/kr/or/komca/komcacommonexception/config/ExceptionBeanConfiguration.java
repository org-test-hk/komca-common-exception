package kr.or.komca.komcacommonexception.config;

import kr.or.komca.komcacommonexception.handler.BaseGlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionBeanConfiguration {

	@Bean
	public BaseGlobalExceptionHandler BaseGlobalExceptionHandler() {
		return new BaseGlobalExceptionHandler();
	}
}
