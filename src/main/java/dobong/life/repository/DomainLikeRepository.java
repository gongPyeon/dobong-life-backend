package dobong.life.repository;

import dobong.life.entity.Category;
import dobong.life.entity.Domain;
import dobong.life.entity.DomainLike;
import dobong.life.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DomainLikeRepository extends JpaRepository<DomainLike, Long> {
    Optional<DomainLike> findByDomainAndUser(Domain domain, User user);

    List<DomainLike> findByUser(User user);

    @Query("SELECT DISTINCT dl FROM DomainLike dl " + "JOIN FETCH dl.domain d " + "JOIN FETCH d.category c " + "WHERE dl.user = :user AND c = :category")
    List<DomainLike> findByUserJoinCategory(User user, Category category);
}
