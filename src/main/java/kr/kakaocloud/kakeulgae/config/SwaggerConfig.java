package kr.kakaocloud.kakeulgae.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "API 명세서",
        description = "IT's GOAL KEEPER API 명세서",
        version = "v1"))
@Configuration //스프링 컨테이너에게 해당 클래스를 Bean 구성 클래스임을 알려준다.
public class SwaggerConfig {

}
