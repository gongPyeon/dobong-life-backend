package dobong.life.repository;

import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Domain, Long> {
    Optional<List<Domain>> findByCategory(Category category);
}
