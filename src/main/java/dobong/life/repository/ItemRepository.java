package dobong.life.repository;

import dobong.life.entity.Domain;
import dobong.life.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<String> findByDomain(Domain domain);
}
