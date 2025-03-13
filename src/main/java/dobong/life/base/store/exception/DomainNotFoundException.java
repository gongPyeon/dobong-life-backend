package dobong.life.base.store.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class DomainNotFoundException extends BaseException {
    public DomainNotFoundException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
