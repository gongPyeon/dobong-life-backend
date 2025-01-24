package dobong.life.repository;

import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Override
    List<Tag> findAll();

    List<Tag> findByCategory(Category category);

    @Query("SELECT t FROM Tag t WHERE t.category = :category AND t.category.name LIKE %:query%")
    List<Tag> findByCategoryAndQuery(Category category, String query);

    List<Tag> findByIdAndCategoryAndSubTagName(Long id,Category category, String subTagName);

    List<String> findByDomain(Domain domain);
}
