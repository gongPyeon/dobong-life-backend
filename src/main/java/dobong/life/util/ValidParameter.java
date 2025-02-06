package dobong.life.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ParameterValidator.class)
@Documented
public @interface ValidParameter{
    String message() default "요청값이 유효하지 않습니다";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
