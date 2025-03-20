package dobong.life.global.img.exception;

import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;

public class S3BadRequestException extends BaseException {
    public S3BadRequestException(BaseErrorCode status, String message) {
        super(status, message);
    }
}
