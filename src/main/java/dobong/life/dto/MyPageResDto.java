package dobong.life.dto;

import dobong.life.dto.info.LikeCount;
import dobong.life.entity.User;
import lombok.Getter;

@Getter
public class MyPageResDto {
    private String email;
    private String name;
    private int myReviewCount;
    private int myReviewLikeCount;
    private int myFoodLike;
    private int myPlaceLike;
    private int myBusinessLike;

    public MyPageResDto(User user, int reviewCount, int reviewLikeCount, LikeCount likeCount) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.myReviewCount = reviewCount;
        this.myReviewLikeCount = reviewLikeCount;
        this.myFoodLike = likeCount.getFoodLikeCount();
        this.myPlaceLike = likeCount.getPlaceLikeCount();
        this.myBusinessLike = likeCount.getBusinessLikeCount();
    }
}
