package kr.kakaocloud.kakeulgae.config;

import java.util.List;
import kr.kakaocloud.kakeulgae.security.LoginUserIdResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
class AuthenticationConfig implements WebMvcConfigurer {
    private final LoginUserIdResolver loginUserIdResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserIdResolver);
    }
}
