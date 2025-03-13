package dobong.life.base.review.repository;

import dobong.life.base.review.Review;
import dobong.life.base.store.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findByDomain(Domain domain);

    @Query("SELECT r FROM Review r " + "WHERE r.user.id = :userId")
    List<Review> findByUserId(Long userId);
}
