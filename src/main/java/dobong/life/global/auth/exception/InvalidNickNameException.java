package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class InvalidNickNameException extends BaseException {
    public InvalidNickNameException(BaseErrorCode error) {
        super(error, error.getMessage());
    }
}
