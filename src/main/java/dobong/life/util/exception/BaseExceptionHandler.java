package dobong.life.util.exception;

import dobong.life.util.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class BaseExceptionHandler {

    private final BaseResponseService baseResponseService;
    @ExceptionHandler({MessageException.class})
    public BaseResponse<Object> handle_MessagingException(MessageException e){
        log.error("MessagingExceptionHandler.handle_MessagingException <{}> {}", e.getStatus().getMessage(), e);
        return baseResponseService.getErrorResponse(e.getStatus());
    }
}
