package dobong.life.repository;

import dobong.life.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Domain, Long> {
}
