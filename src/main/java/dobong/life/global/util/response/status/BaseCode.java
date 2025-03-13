package dobong.life.global.util.response.status;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseCode implements StatusCode{
    OK(200, "요청에 성공했습니다."),
    SUCCESS_LOGOUT(200, "로그아웃을 성공했어요"),
    SUCCESS_LOGIN(200, "로그인에 성공했어요"),
    SUCCESS_REVIEW(200, "리뷰를 성공적으로 삭제했어요");

    private int code;
    private String message;
    private HttpStatus httpStatus;

    BaseCode(int code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.OK;
    }
}
