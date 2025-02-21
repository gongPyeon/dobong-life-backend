package dobong.life.domain.store.repository;

import dobong.life.domain.store.Domain;
import dobong.life.domain.store.MiddleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Long> {
    List<MiddleCategory> findByDomain(Domain domain);
}
