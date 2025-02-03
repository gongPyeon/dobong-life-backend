package dobong.life.service;

import dobong.life.dto.info.CountDetails;
import dobong.life.entity.User;
import dobong.life.repository.ReviewLikeRepository;
import dobong.life.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageQueryService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    private final static long FOOD_ID = 1;
    private final static long PLACE_ID = 2;
    private final static long BUSINESS_ID = 3;
    public CountDetails getCountDetails(User user) {

        return CountDetails.builder()
                .myReviewCount(getReviewCount(user))
                .myReviewLikeCount(getReviewLikeCount(user))
                .myFoodLike(getMyCategoryLikeCount(user, FOOD_ID))
                .myPlaceLike(getMyCategoryLikeCount(user, PLACE_ID))
                .myBusinessLike(getMyCategoryLikeCount(user, BUSINESS_ID))
                .build();
    }

    private int getMyCategoryLikeCount(User user, long categoryId) {
        return (int) reviewLikeRepository.findByUserAndId(user, categoryId).stream().count();
    }
    private int getReviewLikeCount(User user) {
        return (int) reviewLikeRepository.findByUser(user).stream().count();
    }

    private int getReviewCount(User user) {
        return (int) reviewRepository.findByUser(user).stream().count();
    }
}
