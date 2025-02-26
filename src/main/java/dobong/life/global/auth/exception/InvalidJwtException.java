package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.security.auth.message.AuthException;

public class InvalidJwtException extends BaseException {
    public InvalidJwtException(BaseErrorCode error) {
        super(error, error.getMessage());
    } // AuthException

}
