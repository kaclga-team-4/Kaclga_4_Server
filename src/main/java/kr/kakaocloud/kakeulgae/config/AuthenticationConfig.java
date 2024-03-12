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
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) { //resolvers란 핸들러 메소드의 파라미터를 바인딩하는 역할을 하는 인터페이스
        resolvers.add(loginUserIdResolver); //로그인한 사용자의 ID를 파라미터로 받을 수 있도록 설정
    }
}
