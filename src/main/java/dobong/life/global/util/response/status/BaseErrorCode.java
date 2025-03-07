package dobong.life.global.util.response.status;

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

    INVALID_OAUTH2(403, "[ERROR] 지원하지 않는 소셜 로그인입니다", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(403, "[ERROR] 유효하지 않은 토큰입니다", HttpStatus.FORBIDDEN),
    DB_NOT_SAVE(500, "[ERROR] 데이터 저장을 실패했습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATED_VALUE(400, "[ERROR] 중복된 요청입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATED_EMAIL(400, "이미 존재하는 아이디에요", HttpStatus.BAD_REQUEST),
    DUPLICATED_NICKNAME(400, "이미 존재하는 닉네임이에요", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(400, "비밀번호가 달라요", HttpStatus.BAD_REQUEST),
    INVALID_NICKNAME(400, "[ERROR] 닉네임은 알파벳, 한글, 숫자만 포함할 수 있습니다.", HttpStatus.BAD_REQUEST),
    INVALID_ID(400, "[ERROR] 아이디는 알파벳, 숫자만 포함할 수 있습니다.", HttpStatus.BAD_REQUEST),
    FAIL_LOGIN(400, "로그인에 실패했어요", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
