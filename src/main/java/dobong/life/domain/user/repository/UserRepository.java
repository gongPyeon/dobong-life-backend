package dobong.life.domain.user.repository;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import dobong.life.domain.user.User;
import dobong.life.global.auth.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNickName(String nickName);

    @Query("SELECT u FROM User u " + "WHERE u.providerId = :providerId " + "AND u.providerType = :providerType")
    Optional<User> findByProviderIdAndProviderType(String providerId, SocialType providerType);
}
