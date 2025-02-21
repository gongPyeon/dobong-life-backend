package dobong.life.domain.store.repository;

import dobong.life.domain.store.MiddleTag;
import dobong.life.domain.review.Review;
import dobong.life.domain.review.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiddleTagRepository extends JpaRepository<MiddleTag, Long> {
    List<MiddleTag> findByReview(Review review);

    List<MiddleTag> findByReviewTagAndReview(ReviewTag reviewTag, Review review);
}
