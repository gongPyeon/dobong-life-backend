package dobong.life.domain.store.repository;

import dobong.life.domain.store.Category;
import dobong.life.domain.store.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByCategory(Category category);

    List<Tag> findByCategoryAndId(Category category, Long tagId);
}
