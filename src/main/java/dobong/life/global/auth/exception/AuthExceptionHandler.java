package dobong.life.global.auth.exception;

import dobong.life.domain.like.exception.DuplicateException;
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
        return BaseErrorResponse.of(BaseErrorCode.DUPLICATED_EMAIL);
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    public ResponseEntity<BaseErrorResponse> handle_DuplicateNicknameException(DuplicateNicknameException e) {
        log.error("DuplicateNicknameExceptionHandler.handle_DuplicateNicknameException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(BaseErrorCode.DUPLICATED_NICKNAME);
    }
}
