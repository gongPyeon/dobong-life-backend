package dobong.life.global.util.response.status;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseCode implements StatusCode{ // 필요성이 있을까
    OK(200, "요청에 성공했습니다.");

    private int code;
    private String message;
    private HttpStatus httpStatus;

    BaseCode(int code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.OK;
    }
}
