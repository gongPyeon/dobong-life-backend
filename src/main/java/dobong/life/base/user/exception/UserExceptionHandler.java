package dobong.life.base.user.exception;

import dobong.life.base.store.exception.DomainNotFoundException;
import dobong.life.global.util.response.BaseErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> handle_UserNotFoundException(UserNotFoundException e) {
        log.error("UserNotFoundExceptionHandler.handle_UserNotFoundException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }
}
