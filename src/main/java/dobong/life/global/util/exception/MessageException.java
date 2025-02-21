package dobong.life.global.util.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class MessageException extends BaseException {
    public MessageException(BaseErrorCode statusCode) {
        super(statusCode, statusCode.getMessage());
    }
}


