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
import org.springframework.web.filter.OncePerRequestFilter;

public class FirebaseTokenFilter extends OncePerRequestFilter {
    public static List<String> FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS = List.of("/api/v1/auth/**", "/ready","/test/**");
    private final String BEARER = "Bearer ";

    public FirebaseTokenFilter(UserCustomDetailsService userDetailsService, FirebaseTokenHelper firebaseTokenHelper) {
        this.firebaseTokenHelper = firebaseTokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Data
    class TokenInvalidErrorResponse {
        int code = ErrorCode.INVALID_FIREBASE_ID_TOKEN.code;
        String errorMessage = ErrorCode.INVALID_FIREBASE_ID_TOKEN.errorMessage;
    }

    UserCustomDetailsService userDetailsService;
    FirebaseTokenHelper firebaseTokenHelper;

    private void setErrorResponse(HttpServletResponse response, RuntimeException ex) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), new TokenInvalidErrorResponse());
    }

    private boolean isPermittedRequest(HttpServletRequest request) {
        return FIREBASE_TOKEN_FILTER_PERMITTED_PATTERNS.stream().anyMatch(
            (Predicate<String>) s -> request.getRequestURI().startsWith(s.replaceFirst("/\\*\\*", ""))
        );
    }

    private String getIdTokenByFirebaseToken(String firebaseToken) {
        if (!firebaseToken.startsWith(BEARER)) {
            throw new IllegalArgumentException("$firebaseToken: Bearer 형식의 토큰이 아닙니다");
        }
        return firebaseToken.split(" ")[1];
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        if (!isPermittedRequest(request)) {
            try {
                String firebaseToken = request.getHeader(HttpHeaders.AUTHORIZATION);
                String idToken = getIdTokenByFirebaseToken(firebaseToken);
                String username = firebaseTokenHelper.getUid(idToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (RuntimeException ex) {
                setErrorResponse(response, ex);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
