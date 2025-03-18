package dobong.life.base.store.repository;

import dobong.life.base.store.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT d.tag FROM Domain d " + "WHERE d.category.categoryName = :categoryName")
    Optional<List<Tag>> findAll(String categoryName);
}
