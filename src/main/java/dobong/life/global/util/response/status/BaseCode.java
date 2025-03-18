package dobong.life.global.util.response.status;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseCode implements StatusCode{
    OK(200, "요청에 성공했습니다."),
    SUCCESS_LOGOUT(200, "로그아웃을 성공했어요"),
    SUCCESS_LOGIN(200, "로그인에 성공했어요"),
    SUCCESS_DELETE_REVIEW(200, "리뷰를 성공적으로 삭제했어요"),
    SUCCESS_BUSINESS(200, "성공적으로 인증되었어요"),
    SUCCESS_BUSINESS_STORE(200, "장소 등록 요청이 완료되었어요"),
    SUCCESS_SAVE_REVIEW(200, "리뷰를 성공적으로 저장했어요"),
    SUCCESS_UPDATE_LIKE(200, "좋아요가 적용됐어요"),
    SUCCESS_SIGN_UP(200, "회원가입을 완료했어요"),
    SUCCESS_DELETE_IMG(200, "성공적으로 이미지가 삭제되었어요"),
    SUCCESS_FIX_IMG(200, "성공적으로 이미지가 변경되었어요"),
    DUP_ID_OK(200, "성공적으로 이미지가 변경되었어요"),
    DUP_NAME_OK(200, "성공적으로 이미지가 변경되었어요");


    private int code;
    private String message;
    private HttpStatus httpStatus;

    BaseCode(int code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.OK;
    }
}
