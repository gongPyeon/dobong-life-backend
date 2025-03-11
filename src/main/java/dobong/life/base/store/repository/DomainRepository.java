package dobong.life.base.store.repository;

import dobong.life.base.store.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Long> {
}
