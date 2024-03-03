package kr.kakaocloud.kakeulgae.config;

import kr.kakaocloud.kakeulgae.handler.CustomAuthenticationEntryPoint;
import kr.kakaocloud.kakeulgae.security.filter.FirebaseTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
        FirebaseTokenFilter firebaseTokenFilter = new FirebaseTokenFilter();
        return http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            .sessionManagement(ss->ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(
                String.valueOf(firebaseTokenFilter.FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS)).permitAll().anyRequest().authenticated())
            .addFilterBefore(firebaseTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(it-> it.authenticationEntryPoint(customAuthenticationEntryPoint)).build();
    }
}
