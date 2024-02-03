package kr.kakaocloud.kakeulgae.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "API 명세서",
        description = "개인 맞춤 취업 공고 알림 서비스 API 명세서",
        version = "v1"))
@Configuration
public class SwaggerConfig {

}
