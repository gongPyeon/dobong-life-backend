package dobong.life.repository;

import dobong.life.entity.Domain;
import dobong.life.entity.DomainLike;
import dobong.life.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DomainLikeRepository extends JpaRepository<DomainLike, Long> {
    Optional<DomainLike> findByDomainAndUser(Domain domain, User user);

    List<DomainLike> findByUser(User user);
}
