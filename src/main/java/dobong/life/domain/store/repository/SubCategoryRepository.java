package dobong.life.domain.store.repository;

import dobong.life.domain.store.Category;
import dobong.life.domain.store.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByCategory(Category category);
}
