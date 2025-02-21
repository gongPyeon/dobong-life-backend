package dobong.life.util.exception;

public class S3BadRequestException extends RuntimeException{
    public S3BadRequestException(String message) {
        super(message);
    }
}
