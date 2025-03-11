package dobong.life.base.store.repository;

import dobong.life.base.store.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
