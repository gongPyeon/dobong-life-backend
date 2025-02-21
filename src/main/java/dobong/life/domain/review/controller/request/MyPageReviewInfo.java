package dobong.life.domain.review.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MyPageReviewInfo {

    @NotNull(message = "가게 ID는 필수입니다")
    private Long storeId;
    private String name;

    @NotNull(message = "키워드는 필수입니다")
    @Size(min = 1, max = 5, message = "키워드는 1개 이상 5개 이하로 선택해주세요")
    private List<String> selectedKeywords;

    @NotBlank(message = "리뷰 내용은 필수입니다")
    @Size(min = 10, max = 500, message = "리뷰는 10자 이상 500자 이하로 작성해주세요")
    private String reviewContent;

    public static MyPageReviewInfo create(Long storeId){
        return MyPageReviewInfo.builder()
                .storeId(storeId)
                .name("test")
                .selectedKeywords(List.of("good", "bad"))
                .reviewContent("리뷰를 10자 이상 적는건 힘들어요")
                .build();
    }
}
