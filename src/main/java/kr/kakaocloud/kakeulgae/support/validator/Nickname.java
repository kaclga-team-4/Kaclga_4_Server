package kr.kakaocloud.kakeulgae.support.validator;


import jakarta.validation.Constraint;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

class Nickname {

        private final String NICKNAME_REGEXP = "[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9\\s]+";
        private final  NICKNAME_PATTERN =Pattern.compile(NICKNAME_REGEXP);

        @MustBeDocumented
        @Target(AnnotationTarget.FIELD)
        @Retention(AnnotationRetention.RUNTIME)
        @Constraint(validatedBy =[NicknameValidator::class])

    annotation class Nickname(
            val message:String="유효하지 않은 닉네임 패턴입니다",
            val groups:Array<KClass<*>>=[],
            val payload:Array<KClass<out Payload>>=[],
            )

        class NicknameValidator extends ConstraintValidator<Nickname, String?>
        {
            override fun initialize(contactNumber:Nickname){
        }
            override fun isValid(
            contactField:String ?,
            cxt:ConstraintValidatorContext ?,
    ):Boolean {
            return !contactField.isNullOrBlank() && NICKNAME_PATTERN.matcher(contactField).matches()
        }
        }
    }