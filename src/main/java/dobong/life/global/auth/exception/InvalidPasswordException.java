package dobong.life.global.auth.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException(BaseErrorCode error) {
        super(error, error.getMessage());
    }
}
