package dobong.life.repository;

import dobong.life.entity.Review;
import dobong.life.entity.ReviewLike;
import dobong.life.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    Optional<ReviewLike> findByUserAndReview(User user, Review review);

    List<ReviewLike> findByUser(User user);

    @Query("SELECT r FROM ReviewLike r " + "WHERE r.review.domain.category.id = :categoryId")
    List<ReviewLike> findByUserAndId(User user, long categoryId);
}
