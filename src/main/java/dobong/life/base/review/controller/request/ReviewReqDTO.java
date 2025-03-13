package dobong.life.base.review.controller.request;

import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@AllArgsConstructor
@Getter
@Validated
public class ReviewReqDTO {
    @NotNull(message = "가게 ID는 필수입니다")
    private Long storeId;
    private String name;

    private double rating;

    @NotNull(message = "키워드는 필수입니다")
    @Size(min = 1, max = 5, message = "키워드는 1개 이상 5개 이하로 선택해주세요")
    private List<String> selectedKeywords;

    @NotBlank(message = "리뷰 내용은 필수입니다")
    @Size(min = 10, max = 500, message = "리뷰는 10자 이상 500자 이하로 작성해주세요")
    private String reviewContent;

    private String img;

}
