package dobong.life.repository;

import dobong.life.entity.Domain;
import dobong.life.entity.Favorite;
import dobong.life.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByDomainAndUser(Domain domain, User user);
}
