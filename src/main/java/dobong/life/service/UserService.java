package dobong.life.service;

import dobong.life.dto.RegisterResponse;
import dobong.life.dto.RegisterUserCommand;
import dobong.life.dto.UserSignUpDto;
import dobong.life.entity.User;
import dobong.life.enums.Role;
import dobong.life.repository.UserRepository;
import dobong.life.util.exception.DuplicateException;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(UserSignUpDto userSignUpDto){
        if(userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()){
            throw new DuplicateException();
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .name(userSignUpDto.getName())
                .role(Role.ROLE_USER).build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }

    @Transactional
    public RegisterResponse getOrRegisterUser(RegisterUserCommand registerUserCommand) {
        User findUser = userRepository.findByProviderIdAndProviderType(
                registerUserCommand.providerId(),
                registerUserCommand.providerType())
                .orElseGet(()->{
                    User user = User.create(registerUserCommand); // DDD 도메인에 능동성 부여
                    userRepository.save(user);
                    return user;
        });

        return RegisterResponse.from(findUser);
    }
}
