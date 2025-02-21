package dobong.life.domain.like.repository;

import dobong.life.domain.store.Category;
import dobong.life.domain.store.Domain;
import dobong.life.domain.like.DomainLike;
import dobong.life.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DomainLikeRepository extends JpaRepository<DomainLike, Long> {
    Optional<DomainLike> findByDomainAndUser(Domain domain, User user);

    List<DomainLike> findByUser(User user);

    @Query("SELECT DISTINCT dl FROM DomainLike dl " + "JOIN FETCH dl.domain d " + "JOIN FETCH d.category c " + "WHERE dl.user = :user AND c = :category")
    List<DomainLike> findByUserJoinCategory(User user, Category category);
}
