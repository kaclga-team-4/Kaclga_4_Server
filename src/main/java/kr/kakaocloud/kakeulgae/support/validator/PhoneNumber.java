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

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default  "유효하지 않은 전화번호 패턴입니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private final String PHONE_NUMBER_REGEXP = "^\\d{3}-\\d{3,4}-\\d{4}$";

    private final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEXP);

    @Override
    public boolean isValid(@Nullable String contactField,
        @Nullable ConstraintValidatorContext constraintValidatorContext) {
        //값이 널이거나 패턴이랑 다르거나
        return contactField == null || PHONE_NUMBER_PATTERN.matcher(contactField).matches();
    }
}
