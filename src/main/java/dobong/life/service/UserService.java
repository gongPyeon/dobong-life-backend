package dobong.life.service;

import dobong.life.dto.RegisterResponse;
import dobong.life.dto.RegisterUserCommand;
import dobong.life.dto.UserSignUpDto;
import dobong.life.entity.User;
import dobong.life.enums.Role;
import dobong.life.repository.UserRepository;
import dobong.life.service.query.UserQueryService;
import dobong.life.util.DEFINE;
import dobong.life.util.exception.DuplicateException;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.FileChannel;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserQueryService userQueryService;
    private final S3Service s3Service;

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
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("[ERROR] 사용자를 찾을 수 없습니다: " + email));

        return RegisterResponse.from(findUser);
    }

    public String uploadImage(Long userId, MultipartFile image) {
        User user = userQueryService.getUserById(userId);
        String imgUrl = s3Service.uploadImage(image);
        user.updateImgUrl(imgUrl);

        return DEFINE.FIX_IMG_OK;
    }

    public String deleteImage(Long userId) {
        User user = userQueryService.getUserById(userId);
        s3Service.deleteImage(user.getImgUrl());
        user.deleteImgUrl();

        return DEFINE.DELETE_IMG_OK;
    }
}
