package dobong.life.util.response;

import com.fasterxml.jackson.databind.ser.Serializers;
import dobong.life.util.response.status.BaseCode;
import dobong.life.util.response.status.StatusCode;
import org.springframework.stereotype.Service;

@Service
public class BaseResponseService {
    public <T> BaseResponse<T> getSuccessResponse(){
        StatusCode statusCode = BaseCode.OK;
        return new BaseResponse<>(statusCode);
    }

    public <T> BaseResponse<T> getSuccessResponse(T result){
        StatusCode statusCode = BaseCode.OK;
        return new BaseResponse<>(statusCode, result);
    }

    public <T> BaseResponse<T> getErrorResponse(StatusCode statusCode){
        return new BaseResponse<>(statusCode);
    }
}
