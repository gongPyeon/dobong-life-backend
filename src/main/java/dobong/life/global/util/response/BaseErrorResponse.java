package dobong.life.global.util.response;


import dobong.life.global.util.response.status.StatusCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseErrorResponse {
    private int code;
    private String message;

    public static ResponseEntity<BaseErrorResponse> of(StatusCode code) {
        BaseErrorResponse res = new BaseErrorResponse(code.getCode(), code.getMessage());

        return new ResponseEntity<>(res, code.getHttpStatus());
    }

    public static ResponseEntity<BaseErrorResponse> of(BaseException exception) {
        BaseErrorResponse res = new BaseErrorResponse(exception.getStatus().getCode(), exception.getStatus().getMessage());

        return new ResponseEntity<>(res, exception.getStatus().getHttpStatus());
    }
}
