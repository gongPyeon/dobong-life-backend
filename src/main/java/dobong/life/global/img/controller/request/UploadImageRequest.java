package dobong.life.global.img.controller.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UploadImageRequest(
        @NotNull(message = "이미지는 필수 입력 항목입니다.")
        MultipartFile image
) {
}
