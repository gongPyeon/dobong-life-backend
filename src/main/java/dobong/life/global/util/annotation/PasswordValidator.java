package dobong.life.global.util.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;

        boolean hasLetterCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        // TODO: 정규식을 활용하면 성능은 높아지지만 가독성은 떨어진다, 이렇게 구현하면 성능은 떨어지지만 가독성은 높아진다. 어떻게 해야할까?
        for (char c : value.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetterCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isWhitespace(c)) {
                hasSpecialChar = true;
            }
        }

        return hasLetterCase && hasDigit && hasSpecialChar;
    }
}
