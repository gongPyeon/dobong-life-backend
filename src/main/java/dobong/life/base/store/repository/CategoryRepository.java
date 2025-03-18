package dobong.life.base.store.repository;

import dobong.life.base.store.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT DISTINCT c.categoryName FROM Category c")
    Optional<List<String>> findAllCategoryNames();
}
