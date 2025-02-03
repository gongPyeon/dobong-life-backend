package dobong.life.dto.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CountDetails {
    private int myReviewCount;
    private int myReviewLikeCount;
    private int myFoodLike;
    private int myPlaceLike;
    private int myBusinessLike;
}
