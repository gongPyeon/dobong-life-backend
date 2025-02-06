package dobong.life.service.query;

import dobong.life.entity.User;
import dobong.life.repository.UserRepository;
import dobong.life.util.ValidValue;
import dobong.life.util.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    public User getUserById(long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
