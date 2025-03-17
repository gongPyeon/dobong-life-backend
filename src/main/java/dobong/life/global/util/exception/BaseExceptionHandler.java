package dobong.life.global.util.exception;

import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.status.BaseErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<BaseErrorResponse> handle_DTOValidException(MethodArgumentNotValidException e){
        log.error("DTOValidExceptionHandler.handle_DTOValidException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(BaseErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handle_Exception(Exception e) {
        log.error("ExceptionHandler.handle_Exception <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(BaseErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<BaseErrorResponse> handle_DataAccessException(DataAccessException e) {
        log.error("DataAccessExceptionHandler.handle_DataAccessException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(BaseErrorCode.DB_NOT_SAVE);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseErrorResponse> handle_DataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationExceptionHandler.handle_DataIntegrityViolationException <{}> {}", e.getMessage(), e);
        return BaseErrorResponse.of(BaseErrorCode.DB_NOT_SAVE);
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<BaseErrorResponse> handle_UserNotFoundException(UserNotFoundException e) {
//        log.error("UserNotFoundExceptionHandler.handle_UserNotFoundException <{}> {}", e.getMessage(), e);
//        return BaseErrorResponse.of(BaseErrorCode.NOT_FOUND);
//    }
//
//    @ExceptionHandler(DuplicateException.class)
//    public ResponseEntity<BaseErrorResponse> handle_DuplicateException(DuplicateException e) {
//        log.error("DuplicateExceptionHandler.handle_DuplicateException <{}> {}", e.getMessage(), e);
//        return BaseErrorResponse.of(BaseErrorCode.NOT_FOUND);
//    }
//    @ExceptionHandler(SubCategoryNotFoundException.class)
//    public ResponseEntity<BaseErrorResponse> handle_SubCategoryNotFoundException(SubCategoryNotFoundException e) {
//        log.error("SubCategoryNotFoundExceptionHandler.handle_SubCategoryNotFoundException <{}> {}", e.getMessage(), e);
//        return BaseErrorResponse.of(BaseErrorCode.NOT_FOUND);
//    }


}
