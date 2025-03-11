package dobong.life.base.store.repository;

import dobong.life.base.store.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    @Query("SELECT d FROM Domain d " + "WHERE d.category.id = :categoryId")
    List<Domain> findByCategoryId(Long categoryId);
}
