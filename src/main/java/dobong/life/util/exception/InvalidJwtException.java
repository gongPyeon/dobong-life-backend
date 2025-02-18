package dobong.life.util.exception;

import jakarta.security.auth.message.AuthException;

public class InvalidJwtException extends RuntimeException { // AuthException
    public InvalidJwtException(String message) {
        super(message);
    }
}
