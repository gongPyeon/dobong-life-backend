package dobong.life.base.review.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class ReviewNotFoundException extends BaseException {
    public ReviewNotFoundException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
