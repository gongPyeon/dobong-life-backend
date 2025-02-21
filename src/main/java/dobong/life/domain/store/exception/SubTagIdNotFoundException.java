package dobong.life.domain.store.exception;

import dobong.life.global.util.response.BaseException;

import static dobong.life.global.util.response.status.BaseErrorCode.NOT_FOUND;

public class SubTagIdNotFoundException extends BaseException {
    public SubTagIdNotFoundException(Long subTagId) {
        super(NOT_FOUND, "[ERROR] "+subTagId+"에 해당하는 서브 태그가 존재하지 않습니다");
    }
}
