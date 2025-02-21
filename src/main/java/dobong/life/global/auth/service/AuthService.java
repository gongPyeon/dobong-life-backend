package dobong.life.global.auth.service;

import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.domain.user.User;
import dobong.life.domain.user.repository.UserRepository;
import dobong.life.domain.like.exception.DuplicateException;
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
