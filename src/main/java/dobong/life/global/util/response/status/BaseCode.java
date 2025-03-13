package dobong.life.global.util.response.status;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseCode implements StatusCode{
    OK(200, "요청에 성공했습니다."),
    SUCCESS_LOGOUT(200, "로그아웃을 성공했어요"),
    SUCCESS_LOGIN(200, "로그인에 성공했어요"),
    SUCCESS_REVIEW(200, "리뷰를 성공적으로 삭제했어요"),
    SUCCESS_BUSINESS(200, "성공적으로 인증되었어요"),
    SUCCESS_BUSINESS_STORE(200, "장소 등록 요청이 완료되었어요");

    private int code;
    private String message;
    private HttpStatus httpStatus;

    BaseCode(int code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.OK;
    }
}
