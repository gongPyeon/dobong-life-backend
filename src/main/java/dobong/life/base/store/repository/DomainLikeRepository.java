package dobong.life.base.store.repository;

import dobong.life.base.store.DomainLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainLikeRepository extends JpaRepository<DomainLike, Long> {
}
