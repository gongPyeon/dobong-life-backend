package dobong.life.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ParameterValidator implements ConstraintValidator<ValidParameter, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;

        if(value instanceof String){
            String stringValue = (String) value;
            return !stringValue.trim().isEmpty();
        }

        if(value instanceof Long){
            Long longValue = (Long) value;
            return longValue > 0;
        }

        return true;
    }
}
