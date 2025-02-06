package dobong.life.util.exception;

import dobong.life.util.response.BaseException;
import dobong.life.util.response.status.BaseErrorCode;

import static dobong.life.util.response.status.BaseErrorCode.DUPLICATED_VALUE;

public class DuplicateException extends BaseException {
    public DuplicateException(String domainName) {
        super(DUPLICATED_VALUE, "[ERROR] "+domainName +"에 대한 좋아요 버튼을 중복해서 요청했습니다");
    }

    public DuplicateException() {
        super(DUPLICATED_VALUE, DUPLICATED_VALUE.getMessage());
    }
}
