package dobong.life.domain.user.repository;

import dobong.life.domain.user.User;
import dobong.life.global.auth.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u " + "WHERE u.providerId = :providerId " + "AND u.providerType = :providerType")
    Optional<User> findByProviderIdAndProviderType(String providerId, SocialType providerType);
}
