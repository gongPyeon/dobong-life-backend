package dobong.life.base.store.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class HashTagNotFoundException extends BaseException {
    public HashTagNotFoundException(BaseErrorCode status, String message) {
        super(status, message);
    }
}