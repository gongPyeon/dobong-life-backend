package dobong.life.base.user.repository;

import dobong.life.base.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
