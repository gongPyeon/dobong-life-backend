package dobong.life.repository;

import dobong.life.entity.Category;
import dobong.life.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Override
    List<Tag> findAll();

    List<Tag> findByCategory(Category category);
}
