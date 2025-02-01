package dobong.life.repository;

import dobong.life.entity.Review;
import dobong.life.entity.ReviewLike;
import dobong.life.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    Optional<ReviewLike> findByUserAndReview(User user, Review review);
}
