package dobong.life.domain.user.service;

import dobong.life.domain.like.service.query.ReviewLikeQueryService;
import dobong.life.domain.review.service.query.ReviewQueryService;
import dobong.life.domain.store.Category;
import dobong.life.domain.store.service.query.CategoryQueryService;
import dobong.life.domain.store.service.query.TagQueryService;
import dobong.life.domain.user.User;
import dobong.life.domain.user.controller.response.MyPageResDto;
import dobong.life.domain.user.controller.response.MyPageReviewResDto;
import dobong.life.domain.user.service.query.MyPageQueryService;
import dobong.life.domain.user.service.query.UserQueryService;
import dobong.life.domain.like.dto.LikeCount;
import dobong.life.domain.review.controller.request.MyPageReviewInfo;
import dobong.life.domain.store.dto.StoreBasicInfo;
import dobong.life.global.util.constant.DEFINE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {

    private final TagQueryService tagQueryService;
    private final MyPageQueryService myPageQueryService;
    private final ReviewQueryService reviewQueryService;
    private final UserQueryService userQueryService;
    private final ReviewLikeQueryService reviewLikeQueryService;
    private final CategoryQueryService categoryQueryService;

    public MyPageResDto getMyPage(Long userId) {
        User user = userQueryService.getUserById(userId);
        int reviewCount = reviewQueryService.getReviewCount(user);
        int reviewLikeCount = reviewLikeQueryService.getReviewLikeCount(user);
        LikeCount likeCount= getLikeCount(user);

        return new MyPageResDto(user, reviewCount, reviewLikeCount, likeCount);
    }

    private LikeCount getLikeCount(User user) {
        int foodLikeCount = reviewLikeQueryService.getMyCategoryLikeCount(user, DEFINE.FOOD_ID);
        int placeLikeCount = reviewLikeQueryService.getMyCategoryLikeCount(user, DEFINE.PLACE_ID);
        int businessLikeCount = reviewLikeQueryService.getMyCategoryLikeCount(user, DEFINE.BUSINESS_ID);

        return new LikeCount(foodLikeCount, placeLikeCount, businessLikeCount);
    }

    public MyPageReviewResDto getMyReview(Long userId) {
        User user = userQueryService.getUserById(userId);
        List<MyPageReviewInfo> reviews  = myPageQueryService.getMyPageReviewInfoList(user);

        return new MyPageReviewResDto(reviews);
    }

    public MyPageReviewResDto getMyReviewLike(Long userId) {
        User user = userQueryService.getUserById(userId);
        List<MyPageReviewInfo> reviews  = myPageQueryService.getMyPageReviewLikeInfoList(user);

        return new MyPageReviewResDto(reviews);
    }

    public List<StoreBasicInfo> getMyLike(Long categoryId, Long userId) {
        Category category = categoryQueryService.getCategory(categoryId);
        User user = userQueryService.getUserById(userId);
        List<StoreBasicInfo> items = tagQueryService.getStoreInfoWithLimitLike(category, user);

        return items;
    }
}
