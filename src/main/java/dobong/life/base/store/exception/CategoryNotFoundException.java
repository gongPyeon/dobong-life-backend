package dobong.life.base.store.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
