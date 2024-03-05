package kr.kakaocloud.kakeulgae.config;

import static kr.kakaocloud.kakeulgae.security.filter.FirebaseTokenFilter.FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS;

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


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{
   private final UserCustomDetailsService userDetailsService;
    private  final FirebaseTokenHelper firebaseTokenHelper;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    public SecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
         return http.csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            .sessionManagement(ss->ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(it -> it.requestMatchers(FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS.toArray(new String[0])).permitAll().anyRequest().authenticated())
            .addFilterBefore(new FirebaseTokenFilter(userDetailsService, firebaseTokenHelper), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(it-> it.authenticationEntryPoint(customAuthenticationEntryPoint)).build();
    }
}
