package dobong.life.util.response;

import dobong.life.util.response.status.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseException extends Exception {
    public BaseErrorCode status;
}
