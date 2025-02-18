package dobong.life.service;

import dobong.life.dto.UserSignUpDto;
import dobong.life.entity.User;
import dobong.life.repository.UserRepository;
import dobong.life.util.exception.DuplicateException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(UserSignUpDto userSignUpDto){
        if(userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()){
            throw new DuplicateException();
        }

        User user = User.create(userSignUpDto, passwordEncoder);
        userRepository.save(user);
    }
}
