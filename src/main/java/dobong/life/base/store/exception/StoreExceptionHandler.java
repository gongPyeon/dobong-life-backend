package dobong.life.base.store.exception;

import dobong.life.global.auth.exception.DuplicateEmailException;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class StoreExceptionHandler {
    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> handle_DomainNotFoundException(DomainNotFoundException e) {
        log.error("DomainNotFoundExceptionHandler.handle_DomainNotFoundException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }
}