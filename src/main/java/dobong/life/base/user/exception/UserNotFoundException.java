package dobong.life.base.user.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
