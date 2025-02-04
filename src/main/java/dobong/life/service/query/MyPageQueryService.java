package dobong.life.service.query;

import dobong.life.dto.info.CountDetails;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.entity.Review;
import dobong.life.entity.ReviewLike;
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
        return reviewLikeRepository.findByUserAndId(user, categoryId).size();
    }
    private int getReviewLikeCount(User user) {
        return reviewLikeRepository.findByUser(user).size();
    }

    private int getReviewCount(User user) {
        return reviewRepository.findByUser(user).size();
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
                .name("")
                .reviewContent(reviewContent)
                .selectedKeywords(selectedKeywords)
                .build();
    }

    public List<MyPageReviewInfo> getMyPageReviewLikeInfoList(User user) {
        return reviewLikeRepository.findByUser(user).stream()
                .map(this::getMyPageReviewLikeInfo)
                .collect(Collectors.toList());
    }

    private MyPageReviewInfo getMyPageReviewLikeInfo(ReviewLike reviewLike) {
        Review review = reviewLike.getReview();
        long storeId = review.getId();
        String name = review.getUser().getName();
        String reviewContent = review.getContent();
        List<String> selectedKeywords = middleTagRepository.findByReview(review).stream()
                .map(r -> r.getReviewTag().getName()).collect(Collectors.toList());

        return MyPageReviewInfo.builder()
                .storeId(storeId)
                .name(name)
                .reviewContent(reviewContent)
                .selectedKeywords(selectedKeywords)
                .build();
    }
}
