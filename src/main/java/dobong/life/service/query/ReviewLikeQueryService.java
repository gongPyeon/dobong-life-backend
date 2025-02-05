package dobong.life.service.query;

import dobong.life.entity.Category;
import dobong.life.entity.User;
import dobong.life.repository.ReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
