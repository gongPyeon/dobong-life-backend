package dobong.life.service.query;

import dobong.life.dto.info.CountDetails;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.entity.Review;
import dobong.life.entity.User;
import dobong.life.repository.DomainRepository;
import dobong.life.repository.MiddleTagRepository;
import dobong.life.repository.ReviewLikeRepository;
import dobong.life.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageQueryService {

    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;
    private final MiddleTagRepository middleTagRepository;

    private final static long FOOD_ID = 1;
    private final static long PLACE_ID = 2;
    private final static long BUSINESS_ID = 3;
    private final DomainRepository domainRepository;

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

    public List<MyPageReviewInfo> getMyPageReviewInfoList(User user) {
        return reviewRepository.findByUser(user).stream()
                .map(this::getMyPageReviewInfo)
                .collect(Collectors.toList());
    }

    private MyPageReviewInfo getMyPageReviewInfo(Review review) {
        long storeId = review.getDomain().getId();
        String reviewContent = review.getContent();
        List<String> selectedKeywords = middleTagRepository.findByReview(review).stream()
                .map(r -> r.getReviewTag().getName()).collect(Collectors.toList());

        return MyPageReviewInfo.builder()
                .storeId(storeId)
                .reviewContent(reviewContent)
                .selectedKeywords(selectedKeywords)
                .build();
    }
}
