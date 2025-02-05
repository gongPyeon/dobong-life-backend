package dobong.life.service;

import dobong.life.entity.Domain;
import dobong.life.entity.Review;
import dobong.life.entity.User;
import dobong.life.service.query.ReviewQueryService;
import dobong.life.service.query.StoreQueryService;
import dobong.life.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final UserQueryService userQueryService;
    private final StoreQueryService storeQueryService;
    private final ReviewQueryService reviewQueryService;

    public void updateStoreLikeByUser(Long userId, Long storeId) {
        User user = userQueryService.getUserById(userId);
        Domain domain = storeQueryService.getStore(storeId);

        storeQueryService.updateStoreLike(user, domain);
    }

    public void updateReviewLikeByUser(Long userId, Long reviewId) {
        User user = userQueryService.getUserById(userId);
        Review review = reviewQueryService.getReviewById(reviewId);

        storeQueryService.updateReviewLike(user, review);
    }
}
