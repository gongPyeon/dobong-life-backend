package dobong.life.domain.review.repository;

import dobong.life.domain.review.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {
    ReviewTag findByName(String name);
}
