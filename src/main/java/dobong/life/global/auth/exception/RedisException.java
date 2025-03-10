package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class RedisException extends BaseException {
    public RedisException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
