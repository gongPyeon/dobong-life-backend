package dobong.life.global.util.response;

import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {
    public BaseErrorCode status;
    public String message;
}
