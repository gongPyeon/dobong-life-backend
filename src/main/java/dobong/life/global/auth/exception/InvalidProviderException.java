package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.security.auth.message.AuthException;

// TODO: 체크드 예외를 사용해야할지, AuthException
public class InvalidProviderException extends BaseException {

    public InvalidProviderException(BaseErrorCode error) {
        super(error, error.getMessage());
    }
}
