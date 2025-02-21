package dobong.life.domain.like.repository;

import dobong.life.domain.review.Review;
import dobong.life.domain.like.ReviewLike;
import dobong.life.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    Optional<ReviewLike> findByUserAndReview(User user, Review review);

    List<ReviewLike> findByUser(User user);

    @Query("SELECT r FROM ReviewLike r " + "WHERE r.review.domain.category.id = :categoryId")
    List<ReviewLike> findByUserAndCategoryId(User user, long categoryId);
}
