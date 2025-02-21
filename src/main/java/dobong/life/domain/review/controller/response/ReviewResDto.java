package dobong.life.domain.review.controller.response;

import dobong.life.domain.review.dto.ReviewInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@AllArgsConstructor
@Validated
public class ReviewResDto {

    @NotNull
    private Long categoryId;
    @NotNull
    private Long storeId;
    private ReviewInfo result;
}
