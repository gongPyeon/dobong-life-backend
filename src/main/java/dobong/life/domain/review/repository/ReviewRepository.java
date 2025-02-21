package dobong.life.domain.review.repository;

import dobong.life.domain.store.Domain;
import dobong.life.domain.review.Review;
import dobong.life.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDomain(Domain domain);

    List<Review> findByUser(User user);
}
