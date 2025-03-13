package dobong.life.base.review.service.query;

import dobong.life.base.review.repository.ReviewLikeRepository;
import dobong.life.base.review.repository.ReviewRepository;
import dobong.life.base.review.Review;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    public List<String> findAllBy(){
        return null;
    }

    @Transactional
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }
}
