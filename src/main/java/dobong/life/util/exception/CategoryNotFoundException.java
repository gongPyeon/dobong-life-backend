package dobong.life.util.exception;

import dobong.life.util.response.BaseException;
import dobong.life.util.response.status.BaseErrorCode;

import static dobong.life.util.response.status.BaseErrorCode.NOT_FOUND;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException(Long categoryId) {
        super(NOT_FOUND, "[ERROR] "+categoryId+"에 해당하는 카테고리를 찾을 수 없습니다");
    }
}
