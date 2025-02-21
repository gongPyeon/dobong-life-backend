package dobong.life.domain.like.exception;

import dobong.life.global.util.response.BaseException;

import static dobong.life.global.util.response.status.BaseErrorCode.DUPLICATED_VALUE;

public class DuplicateException extends BaseException {
    public DuplicateException(String domainName) {
        super(DUPLICATED_VALUE, "[ERROR] "+domainName +"에 대한 좋아요 버튼을 중복해서 요청했습니다");
    }

    public DuplicateException() {
        super(DUPLICATED_VALUE, DUPLICATED_VALUE.getMessage());
    }
}
