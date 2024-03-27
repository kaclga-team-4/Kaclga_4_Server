package kr.kakaocloud.kakeulgae.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import kr.kakaocloud.kakeulgae.security.FirebaseTokenHelper;
import kr.kakaocloud.kakeulgae.security.UserCustomDetailsService;
import kr.kakaocloud.kakeulgae.support.exception.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

public class FirebaseTokenFilter extends
    OncePerRequestFilter { // OncePerRequestFilter는 요청당 한번만 실행되는 필터를 만들기 위한 추상 클래스 / FirebaseTokenFilter는 요청에 대한 토큰을 검증하는 필터

    public static List<String> FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS = List.of("/api/v1/auth/**",
        "/ready", "/test/**", "/swagger-ui/**", "/v3/api-docs/**"); //허용되는 패턴
    private final String BEARER = "Bearer ";

    public FirebaseTokenFilter(UserCustomDetailsService userDetailsService,
        FirebaseTokenHelper firebaseTokenHelper) {
        this.firebaseTokenHelper = firebaseTokenHelper;
        this.userDetailsService = userDetailsService;
    }

    private void setErrorResponse(HttpServletResponse response, RuntimeException ex)
        throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 응답의 컨텐츠 타입을 json으로 설정
        if (ex instanceof UsernameNotFoundException) {
            new ObjectMapper().writeValue(response.getOutputStream(),
                new UserNotFoundErrorResponse());
            return;
        }
        new ObjectMapper().writeValue(response.getOutputStream(), new TokenInvalidErrorResponse());
    }

    private boolean isPermittedRequest(HttpServletRequest request) {
        return FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS.stream().anyMatch(
            (Predicate<String>) s -> request.getRequestURI()
                .startsWith(s.replaceFirst("/\\*\\*", "")) //허용되는 패턴이면 true
        );
    }

    UserCustomDetailsService userDetailsService;
    FirebaseTokenHelper firebaseTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        if (!isPermittedRequest(request)) {
            try {
                String firebaseToken = request.getHeader(HttpHeaders.AUTHORIZATION); //헤더에서 토큰을 가져옴
                String idToken = getIdTokenByFirebaseToken(firebaseToken);
                String username = firebaseTokenHelper.getUid(idToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(
                    username); //스프링 시큐리티에 user를 등록하기 위해 유저 정보를 가져옴
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());//인증 토큰 생성
                SecurityContextHolder.getContext().setAuthentication(
                    authenticationToken); //인증 토큰을 시큐리티 컨텍스트에 저장(시큐리티 컨텍스트란 시큐리티가 제공하는 인증과 권한 정보를 담고 있는 저장소)
            } catch (UsernameNotFoundException ex) {
                setErrorResponse(response, ex);
            } catch (RuntimeException ex) {
                setErrorResponse(response, ex); //예외 발생시 에러 응답
                return;
            }
        }

        filterChain.doFilter(request, response); //다음 필터로 넘김
    }

    @Data
    private class TokenInvalidErrorResponse {

        int code = ErrorCode.INVALID_FIREBASE_ID_TOKEN.code;
        String errorMessage = ErrorCode.INVALID_FIREBASE_ID_TOKEN.errorMessage;
    }

    private String getIdTokenByFirebaseToken(String firebaseToken) {
        if (!firebaseToken.startsWith(BEARER)) {
            throw new IllegalArgumentException("$firebaseToken: Bearer 형식의 토큰이 아닙니다");
        }
        return firebaseToken.split(" ")[1];
    }

    @Data
    private class UserNotFoundErrorResponse {

        int code = ErrorCode.USER_NOT_FOUND.code;
        String errorMessage = ErrorCode.USER_NOT_FOUND.errorMessage;
    }
}
