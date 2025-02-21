package dobong.life.domain.store.exception;

import dobong.life.global.util.response.BaseException;

import static dobong.life.global.util.response.status.BaseErrorCode.NOT_FOUND;

public class SubCategoryNotFoundException extends BaseException {
    public SubCategoryNotFoundException(String name) {
        super(NOT_FOUND, "[ERROR] "+name+" 에 해당하는 서브 카테고리가 존재하지 않습니다.");
    }
}
