package dobong.life.controller.dto;

import dobong.life.domain.user.controller.response.MyPageResDto;
import dobong.life.domain.user.controller.response.MyPageReviewResDto;
import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import dobong.life.domain.store.dto.StoreBasicInfo;

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

    public static List<StoreBasicInfo> makeTestGetStoreBasicInfoListDto(Long storeId, String storeName, String storeLocation,
                                                                        String imagUrl, boolean storeLike){

        List<StoreBasicInfo> storeBasicInfos = new ArrayList<>();
        StoreBasicInfo storeBasicInfo = new StoreBasicInfo(storeId, storeName, storeLocation, imagUrl, storeLike);
        storeBasicInfos.add(storeBasicInfo);

        return storeBasicInfos;
    }
}
