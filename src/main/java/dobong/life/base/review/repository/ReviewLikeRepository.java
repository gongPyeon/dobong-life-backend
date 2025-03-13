package dobong.life.base.review.repository;

import dobong.life.base.review.Review;
import dobong.life.base.review.ReviewLike;
import dobong.life.base.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    Optional<ReviewLike> findByUserAndReview(User user, Review review);
}
