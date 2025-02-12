package dobong.life.controller;

import dobong.life.dto.MyPageResDto;

public class TestMyPageControllerResponse {
    public static MyPageResDto makeTestGetMyPageResDto(String email, String name, int myReviewCount, int myReviewLikeCount,
                                                       int myFoodLike, int myPlaceLike, int myBusinessLike) {

        return new MyPageResDto(email, name, myReviewCount, myReviewLikeCount,
                myFoodLike, myPlaceLike, myBusinessLike);
    }
}
