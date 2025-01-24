package dobong.life.repository;

import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByCategory(Category category);
}
