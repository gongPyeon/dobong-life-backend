package dobong.life.base.review.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class KeywordNotFoundException extends BaseException {
    public KeywordNotFoundException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
