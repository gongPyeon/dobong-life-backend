package dobong.life.repository;

import dobong.life.entity.ReviewTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {
    ReviewTag findByName(String name);
}
