package dobong.life.repository;

import dobong.life.entity.Domain;
import dobong.life.entity.Review;
import dobong.life.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDomain(Domain domain);

    List<Review> findByUser(User user);
}
