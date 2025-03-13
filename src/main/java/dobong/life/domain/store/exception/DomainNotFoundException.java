package dobong.life.domain.store.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

import static dobong.life.global.util.response.status.BaseErrorCode.NOT_FOUND;

public class DomainNotFoundException extends BaseException {

    public DomainNotFoundException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
