package dobong.life.base.review.exception;

import dobong.life.base.store.exception.CategoryNotFoundException;
import dobong.life.base.store.exception.DomainNotFoundException;
import dobong.life.base.store.exception.HashTagNotFoundException;
import dobong.life.global.util.response.BaseErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ReviewExceptionHandler {
    @ExceptionHandler(KeywordNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> handle_KeywordNotFoundException(KeywordNotFoundException e) {
        log.error("KeywordNotFoundExceptionHandler.handle_KeywordNotFoundException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<BaseErrorResponse> handle_ReviewNotFoundException(ReviewNotFoundException e) {
        log.error("ReviewNotFoundExceptionHandler.handle_ReviewNotFoundException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }
}
