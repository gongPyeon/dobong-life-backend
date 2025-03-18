package dobong.life.global.auth.exception;

import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AuthExceptionHandler {
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<BaseErrorResponse> handle_DuplicateEmailException(DuplicateEmailException e) {
        log.error("DuplicateEmailExceptionHandler.handle_DuplicateEmailException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<BaseErrorResponse> handle_DuplicateNicknameException(DuplicateNicknameException e) {
        log.error("DuplicateNicknameExceptionHandler.handle_DuplicateNicknameException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(InvalidIDException.class)
    public ResponseEntity<BaseErrorResponse> handle_InvalidIDException(InvalidIDException e) {
        log.error("InvalidIDExceptionHandler.handle_InvalidIDException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<BaseErrorResponse> handle_InvalidJwtException(InvalidJwtException e) {
        log.error("InvalidJwtExceptionExceptionHandler.handle_InvalidJwtException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(InvalidNickNameException.class)
    public ResponseEntity<BaseErrorResponse> handle_InvalidNickNameException(InvalidNickNameException e) {
        log.error("InvalidNickNameExceptionHandler.handle_InvalidNickNameException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<BaseErrorResponse> handle_InvalidPasswordException(InvalidPasswordException e) {
        log.error("InvalidPasswordExceptionHandler.handle_InvalidPasswordException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(InvalidProviderException.class)
    public ResponseEntity<BaseErrorResponse> handle_InvalidProviderException(InvalidProviderException e) {
        log.error("InvalidProviderExceptionHandler.handle_InvalidProviderException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(AuthFailureException.class)
    public ResponseEntity<BaseErrorResponse> handle_AuthFailureException(AuthFailureException e) {
        log.error("AuthFailureExceptionHandler.handle_AuthFailureException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(RedisException.class)
    public ResponseEntity<BaseErrorResponse> handle_RedisException(RedisException e) {
        log.error("RedisExceptionHandler.handle_RedisException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }
}
