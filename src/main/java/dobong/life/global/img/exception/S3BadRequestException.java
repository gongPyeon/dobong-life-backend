package dobong.life.global.img.exception;

public class S3BadRequestException extends RuntimeException{
    public S3BadRequestException(String message) {
        super(message);
    }
}
