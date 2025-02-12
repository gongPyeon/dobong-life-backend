package dobong.life.controller;

import dobong.life.dto.MyPageResDto;
import dobong.life.dto.MyPageReviewResDto;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.dto.info.StoreBasicInfo;

import java.util.ArrayList;
import java.util.List;

public class TestMyPageControllerResponse {
    public static MyPageResDto makeTestGetMyPageResDto(String email, String name, int myReviewCount, int myReviewLikeCount,
                                                       int myFoodLike, int myPlaceLike, int myBusinessLike) {

        return new MyPageResDto(email, name, myReviewCount, myReviewLikeCount,
                myFoodLike, myPlaceLike, myBusinessLike);
    }

    public static MyPageReviewResDto makeTestGetMyPageReviewResDto(Long storeId, String name, List<String> selectedKeywords, String reviewContent){

        List<MyPageReviewInfo> reviews = new ArrayList<>();
        MyPageReviewInfo reviewInfo = new MyPageReviewInfo(storeId, name, selectedKeywords, reviewContent);
        reviews.add(reviewInfo);

        return new MyPageReviewResDto(reviews);
    }
}
