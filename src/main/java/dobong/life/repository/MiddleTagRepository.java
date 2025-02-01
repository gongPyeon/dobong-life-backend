package dobong.life.repository;

import dobong.life.entity.MiddleTag;
import dobong.life.entity.Review;
import dobong.life.entity.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiddleTagRepository extends JpaRepository<MiddleTag, Long> {
    List<MiddleTag> findByReview(Review review);

    List<MiddleTag> findByReviewTagAndReview(ReviewTag reviewTag, Review review);
}
