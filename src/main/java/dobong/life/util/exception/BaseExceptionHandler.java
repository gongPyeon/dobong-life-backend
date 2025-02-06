package dobong.life.util.exception;

import dobong.life.util.response.BaseErrorResponse;
import dobong.life.util.response.BaseResponse;
import dobong.life.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseExceptionHandler {

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<BaseErrorResponse> handle_MessagingException(MessageException e){
        log.error("MessagingExceptionHandler.handle_MessagingException <{}> {}", e.getStatus().getMessage(), e);
        return BaseErrorResponse.of(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> handle_DtoValidException(MethodArgumentNotValidException e){
        log.error("DtoValidExceptionHandler.handle_DtoValidException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(BaseErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleException(Exception e) {
        log.error("ExceptionHandler.handle_Exception <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(BaseErrorCode.BAD_REQUEST);
    }
}
