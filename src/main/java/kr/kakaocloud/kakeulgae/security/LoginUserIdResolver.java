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
    HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUserId.class) && parameter.getParameterType().isAssignableFrom(Long.class);
    }

    @Override
    public Long resolveArgument(@NonNull MethodParameter parameter, ModelAndViewContainer mavContainer,
        @NonNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName() );
    }
}
