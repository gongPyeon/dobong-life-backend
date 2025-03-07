package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class AuthFailureException extends BaseException {
    public AuthFailureException(BaseErrorCode error) {
        super(error, error.getMessage());
    }
}
