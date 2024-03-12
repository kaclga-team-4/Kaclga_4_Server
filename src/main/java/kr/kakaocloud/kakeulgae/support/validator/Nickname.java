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

    Class<?>[] groups() default {}; //groups 는 Bean Validation 에서 제공하는 기능으로, validation 을 그룹핑할 때 사용

    Class<? extends Payload>[] payload() default {}; //payload 는 Bean Validation 에서 제공하는 기능으로, validation 이후에 추가적인 정보를 전달할 때 사용
}

class NicknameValidator implements ConstraintValidator<Nickname, String> {

    private final String NICKNAME_REGEXP = "[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9\\s]+"; //한글, 영문, 숫자, 공백만 허용
    private final Pattern NICKNAME_PATTERN = Pattern.compile(NICKNAME_REGEXP);

    @Override
    public boolean isValid(@Nullable String contactField,
        @Nullable ConstraintValidatorContext constraintValidatorContext) { //isValid 메소드는 실제로 validation 을 수행하는 메소드
        return contactField != null && !contactField.isEmpty() && NICKNAME_PATTERN.matcher(contactField).matches();
    }
}
