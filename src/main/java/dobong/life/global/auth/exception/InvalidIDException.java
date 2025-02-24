package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class InvalidIDException extends BaseException {
    public InvalidIDException(BaseErrorCode error) {
        super(error, error.getMessage());
    }
}