package dobong.life.global.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password{
    String message() default "[ERROR] 비밀번호는 대문자, 소문자, 특수문자, 숫자를 각각 1개 이상 포함해야 합니다";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
