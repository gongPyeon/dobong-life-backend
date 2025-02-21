package dobong.life.domain.store.exception;

import dobong.life.global.util.response.BaseException;

import static dobong.life.global.util.response.status.BaseErrorCode.NOT_FOUND;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException(Long categoryId) {
        super(NOT_FOUND, "[ERROR] "+categoryId+"에 해당하는 카테고리를 찾을 수 없습니다");
    }
}
