package dobong.life.domain.store.exception;

import dobong.life.global.util.response.BaseException;
import static dobong.life.global.util.response.status.BaseErrorCode.NOT_FOUND;

public class DomainNotFoundException extends BaseException {
    public DomainNotFoundException(Long storeId) {
        super(NOT_FOUND, "[ERROR] 상점 아이디 " + storeId + "를 찾을 수 없습니다");
    }
}
