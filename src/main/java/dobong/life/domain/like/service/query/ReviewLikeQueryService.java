package dobong.life.domain.like.service.query;

import dobong.life.domain.user.User;
import dobong.life.domain.like.repository.ReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewLikeQueryService {
    private final ReviewLikeRepository reviewLikeRepository;

    public int getReviewLikeCount(User user) {
        return reviewLikeRepository.findByUser(user).size();
    }

    public int getMyCategoryLikeCount(User user, Long categoryId) {
        return reviewLikeRepository.findByUserAndCategoryId(user, categoryId).size();
    }
}
