package dobong.life.global.util.response;

import dobong.life.global.util.response.status.BaseCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseResponse<T> {

    private int code = BaseCode.OK.getCode(); // 필요할지 고민
    private String message = BaseCode.OK.getMessage(); // 필요할지 고민
    private T result;

    public BaseResponse(T result) {
        this.result = result;
    }

    public BaseResponse(BaseCode baseCode) {
        code = baseCode.getCode();
        message = baseCode.getMessage();
    }
}
