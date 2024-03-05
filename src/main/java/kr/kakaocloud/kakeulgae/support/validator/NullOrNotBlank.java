package kr.kakaocloud.kakeulgae.support.validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nullable;

@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface NullOrNotBlank {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

    @Override
    public boolean isValid(@Nullable String contactField,
        ConstraintValidatorContext constraintValidatorContext) {
        return contactField == null || !contactField.isBlank();
    }
}
