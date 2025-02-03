package dobong.life.dto;

import dobong.life.dto.info.StoreBasicInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

@Getter
@Builder
@AllArgsConstructor
public class MyPageResponseDto {
    private String email;
    private String name;
    private int myReviewCount;
    private int myReviewLikeCount;
    private int myFoodLike;
    private int myPlaceLike;
    private int myBusinessLike;
}
