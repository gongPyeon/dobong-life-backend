package dobong.life.util.response.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseErrorCode implements StatusCode{
    BAD_REQUEST(400, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED),
    FORBIDDEN(403, HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN),
    NOT_FOUND(404, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED(405, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), HttpStatus.METHOD_NOT_ALLOWED),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR),

    DB_NOT_SAVE(500, "[ERROR] 데이터 저장을 실패했습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATED_VALUE(400, "[ERROR] 중복된 요청입니다.", HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
