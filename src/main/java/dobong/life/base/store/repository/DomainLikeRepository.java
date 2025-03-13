package dobong.life.base.store.repository;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import dobong.life.base.store.Domain;
import dobong.life.base.store.DomainLike;
import dobong.life.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DomainLikeRepository extends JpaRepository<DomainLike, Long> {
    @Query("SELECT d FROM DomainLike d WHERE d.user.id = :userId AND d.domain = :domain")
    Optional<DomainLike> findByDomainAndUser(Domain domain, Long userId);

    @Query("SELECT d.domain FROM DomainLike d " + "WHERE d.user.id = :userId")
    List<Domain> findByUserId(Long userId);

}
