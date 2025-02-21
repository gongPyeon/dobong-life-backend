package dobong.life.global.auth.exception;

import jakarta.security.auth.message.AuthException;

// TODO: 체크드 예외를 사용해야할지, AuthException
public class InvalidProviderException extends RuntimeException {

    public InvalidProviderException(String message){
        super(message);
    }
}
