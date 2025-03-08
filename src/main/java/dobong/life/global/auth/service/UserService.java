package dobong.life.global.auth.service;

import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.dto.RegisterUserCommand;
import dobong.life.domain.user.User;
import dobong.life.domain.user.repository.UserRepository;
import dobong.life.domain.user.service.query.UserQueryService;
import dobong.life.global.util.constant.DEFINE;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

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

    public RegisterResponse getRegisterUser(String email) {
        // 등록은 회원가입에서 처리했기 때문
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));

        return RegisterResponse.from(findUser);
    }
}
