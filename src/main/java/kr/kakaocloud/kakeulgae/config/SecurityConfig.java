package kr.kakaocloud.kakeulgae.config;

import static kr.kakaocloud.kakeulgae.security.filter.FirebaseTokenFilter.FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS;

import java.util.Collections;
import kr.kakaocloud.kakeulgae.config.handler.CustomAuthenticationEntryPoint;
import kr.kakaocloud.kakeulgae.security.FirebaseTokenHelper;
import kr.kakaocloud.kakeulgae.security.UserCustomDetailsService;
import kr.kakaocloud.kakeulgae.security.filter.FirebaseTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UserCustomDetailsService userDetailsService; // 사용자 정보를 가져오는 서비스
    private final FirebaseTokenHelper firebaseTokenHelper; // 토큰을 검증하는 서비스
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint; // 인증되지 않은 사용자가 보호된 리소스에 액세스하려고 할 때 호출됨


    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000"));
            config.setAllowCredentials(true);
            return config;
        };
    }

    @Bean
    public SecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
            .sessionManagement(
                ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않음
            .authorizeHttpRequests(it -> it.requestMatchers(
                    FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS.toArray(new String[0])).permitAll()
                .anyRequest().authenticated()) // 필터의 예외 URL을 설정
            .addFilterBefore(new FirebaseTokenFilter(userDetailsService, firebaseTokenHelper),
                UsernamePasswordAuthenticationFilter.class) // addFilterBefore()는 특정 필터를 다른 필터 앞에 추가하는 메소드
            .exceptionHandling(it -> it.authenticationEntryPoint(customAuthenticationEntryPoint))
            .build();// 인증되지 않은 사용자가 보호된 리소스에 액세스하려고 할 때 호출됨
    }
}
