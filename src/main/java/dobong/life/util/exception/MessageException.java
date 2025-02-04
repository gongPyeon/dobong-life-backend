package dobong.life.util.exception;

import dobong.life.util.response.BaseException;
import dobong.life.util.response.status.BaseErrorCode;

public class MessageException extends BaseException {
    public MessageException(BaseErrorCode statusCode) {
        super(statusCode);
    }
}


