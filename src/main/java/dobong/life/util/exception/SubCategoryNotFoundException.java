package dobong.life.util.exception;

import dobong.life.util.response.BaseException;
import dobong.life.util.response.status.BaseErrorCode;

import static dobong.life.util.response.status.BaseErrorCode.NOT_FOUND;

public class SubCategoryNotFoundException extends BaseException {
    public SubCategoryNotFoundException(String name) {
        super(NOT_FOUND, "[ERROR] "+name+" 에 해당하는 서브 카테고리가 존재하지 않습니다.");
    }
}
