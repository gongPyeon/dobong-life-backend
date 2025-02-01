package dobong.life.repository;

import dobong.life.entity.Domain;
import dobong.life.entity.MiddleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Long> {
    List<MiddleCategory> findByDomain(Domain domain);
}
