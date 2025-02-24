package dobong.life.domain.user.controller.response;

import dobong.life.domain.like.dto.LikeCount;
import dobong.life.domain.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@AllArgsConstructor
public class MyPageResDto {

    @NotBlank(message = "[ERROR] 이메일은 비어있을 수 없습니다")
    @Email(message = "유효한 이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "[ERROR] 이름은 비어있을 수 없습니다")
    private String name;

    @Min(value = 0, message = "[ERROR] 리뷰개수는 음수가 될 수 없습니다")
    private int myReviewCount;
    @Min(value = 0, message = "[ERROR] 리뷰 좋아요 개수는 음수가 될 수 없습니다")
    private int myReviewLikeCount;
    @Min(value = 0, message = "[ERROR] 좋아요 상점(음식) 개수는 음수가 될 수 없습니다")
    private int myFoodLike;
    @Min(value = 0, message = "[ERROR] 좋아요 상점(장소) 개수는 음수가 될 수 없습니다")
    private int myPlaceLike;
    @Min(value = 0, message = "[ERROR] 좋아요 상점(비즈니스) 개수는 음수가 될 수 없습니다")
    private int myBusinessLike;

    public MyPageResDto(User user, int reviewCount, int reviewLikeCount, LikeCount likeCount) {
        this.email = user.getEmail();
        this.name = user.getNickName();
        this.myReviewCount = reviewCount;
        this.myReviewLikeCount = reviewLikeCount;
        this.myFoodLike = likeCount.getFoodLikeCount();
        this.myPlaceLike = likeCount.getPlaceLikeCount();
        this.myBusinessLike = likeCount.getBusinessLikeCount();
    }
}
