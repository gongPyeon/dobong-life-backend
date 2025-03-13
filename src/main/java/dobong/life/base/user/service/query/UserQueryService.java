package dobong.life.base.user.service.query;

import dobong.life.base.user.User;
import dobong.life.base.user.exception.UserNotFoundException;
import dobong.life.base.user.repository.UserRepository;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRepository userRepository;

    public User getUserById(long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(BaseErrorCode.NOT_FOUND,
                        "[ERROR] "+userId+"에 해당하는 사용자를 찾을 수 없습니다"));
    }
}
