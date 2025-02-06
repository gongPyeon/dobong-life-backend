package dobong.life.service;

import dobong.life.dto.MyPageResDto;
import dobong.life.dto.MyPageReviewResDto;
import dobong.life.dto.StoresResDto;
import dobong.life.dto.info.LikeCount;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.dto.info.StoreBasicInfo;
import dobong.life.entity.*;
import dobong.life.service.query.*;
import dobong.life.util.DEFINE;
import dobong.life.util.ValidValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public MyPageReviewResDto getMyReview(@ValidValue Long userId) {
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
