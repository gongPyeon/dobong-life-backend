package dobong.life.util.response;

import dobong.life.util.response.status.StatusCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseResponse<T> {

    private int code;
    private String message;
    private T result;

    public BaseResponse(StatusCode statusCode, T result) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.result = result;
    }

    public BaseResponse(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.result = null;
    }
}
