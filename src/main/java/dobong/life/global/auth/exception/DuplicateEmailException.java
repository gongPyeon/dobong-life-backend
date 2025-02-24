package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class DuplicateEmailException extends BaseException {
    public DuplicateEmailException(BaseErrorCode error) {
        super(error, error.getMessage());
    }
}
