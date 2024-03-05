package kr.kakaocloud.kakeulgae.support.validator;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

@Target(ElementType.FIELD)//Annotation 이 어디에 사용 가능한지를 제한하는 데 사용됩니다
@Retention(RetentionPolicy.RUNTIME)//Annotation 의 Scope 를 제한하는데 사용
@Constraint(validatedBy = NicknameValidator.class)
public @interface Nickname {

    String message() default "유효하지 않은 닉네임 패턴입니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class NicknameValidator implements ConstraintValidator<Nickname, String> {

    private final String NICKNAME_REGEXP = "[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9\\s]+";
    private final Pattern NICKNAME_PATTERN = Pattern.compile(NICKNAME_REGEXP);

    @Override
    public boolean isValid(@Nullable String contactField,
        @Nullable ConstraintValidatorContext constraintValidatorContext) {
        return contactField != null && !contactField.isEmpty() && NICKNAME_PATTERN.matcher(contactField).matches();
    }
}
