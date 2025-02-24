package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class DuplicateNicknameException extends BaseException {
    public DuplicateNicknameException(BaseErrorCode error) {
        super(error, error.getMessage());
    }
}
