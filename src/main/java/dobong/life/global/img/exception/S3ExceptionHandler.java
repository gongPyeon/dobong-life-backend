package dobong.life.global.img.exception;

import dobong.life.global.util.exception.MessageException;
import dobong.life.global.util.response.BaseErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class S3ExceptionHandler {

    @ExceptionHandler(S3BadRequestException.class)
    public ResponseEntity<BaseErrorResponse> handle_S3BadRequestException(S3BadRequestException e) {
        log.error("S3BadRequestExceptionHandler.handle_S3BadRequestException <{}> {}", e.getStatus().getMessage(), e);
        return BaseErrorResponse.of(e.getStatus());
    }
}
