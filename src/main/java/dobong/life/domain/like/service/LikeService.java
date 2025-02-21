package dobong.life.domain.like.service;

import dobong.life.domain.store.Domain;
import dobong.life.domain.review.Review;
import dobong.life.domain.user.User;
import dobong.life.domain.review.service.query.ReviewQueryService;
import dobong.life.domain.store.service.query.StoreQueryService;
import dobong.life.domain.user.service.query.UserQueryService;
import dobong.life.global.util.constant.DEFINE;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserQueryService userQueryService;
    private final StoreQueryService storeQueryService;
    private final ReviewQueryService reviewQueryService;

    public String updateStoreLikeByUser(Long userId, Long storeId) {
        User user = userQueryService.getUserById(userId);
        Domain domain = storeQueryService.getDomain(storeId);

        storeQueryService.updateStoreLike(user, domain);

        return DEFINE.LIKE_OK;
    }

    public String updateReviewLikeByUser(Long userId, Long reviewId) {
        User user = userQueryService.getUserById(userId);
        Review review = reviewQueryService.getReviewById(reviewId);

        storeQueryService.updateReviewLike(user, review);

        return DEFINE.LIKE_OK;
    }
}
