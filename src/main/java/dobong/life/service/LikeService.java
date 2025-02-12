package dobong.life.service;

import dobong.life.entity.Domain;
import dobong.life.entity.Review;
import dobong.life.entity.User;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.service.query.ReviewQueryService;
import dobong.life.service.query.StoreQueryService;
import dobong.life.service.query.UserQueryService;
import dobong.life.util.DEFINE;
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
