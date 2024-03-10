package kr.kakaocloud.kakeulgae.security;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LoginUserIdResolver implements
    HandlerMethodArgumentResolver { //로그인한 사용자의 id를 반환하는 클래스

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUserId.class) && parameter.getParameterType().isAssignableFrom(Long.class); //LoginUserId 어노테이션이 붙은 파라미터를 지원
    }

    @Override
    public Long resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
        @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) { //로그인한 사용자의 id를 반환
        // MethodParameter를 통해 파라미터의 정보를 가져옴
        // ModelAndViewContainer는 뷰의 이름과 모델을 저장하고 전달하는 역할
        // NativeWebRequest는 HttpServletRequest와 HttpServletResponse를 대체하는 인터페이스
        // WebDataBinderFactory는 데이터 바인딩을 생성하는 역할
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName() );
    }
}
