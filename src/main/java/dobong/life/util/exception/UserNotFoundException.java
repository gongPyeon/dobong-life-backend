package dobong.life.util.exception;

import dobong.life.util.response.BaseException;
import static dobong.life.util.response.status.BaseErrorCode.NOT_FOUND;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(Long userId) {
        super(NOT_FOUND, "[ERROR] 사용자 아이디 " + userId + "를 찾을 수 없습니다");
    }
}
