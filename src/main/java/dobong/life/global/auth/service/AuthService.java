package dobong.life.global.auth.service;

import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.domain.user.User;
import dobong.life.domain.user.repository.UserRepository;
import dobong.life.domain.like.exception.DuplicateException;
import dobong.life.global.auth.exception.DuplicateEmailException;
import dobong.life.global.auth.exception.DuplicateNicknameException;
import dobong.life.global.auth.exception.InvalidPasswordException;
import dobong.life.global.util.response.status.BaseErrorCode;
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
        userRepository.findByEmail(userSignUpDto.getId())
                .ifPresent(u -> {
                    throw new DuplicateEmailException(BaseErrorCode.DUPLICATED_EMAIL);
                });

        userRepository.findByNickName(userSignUpDto.getNickName())
                .ifPresent(u -> {
                    throw new DuplicateNicknameException(BaseErrorCode.DUPLICATED_NICKNAME);
                });

        if(userSignUpDto.getPwd() != userSignUpDto.getPwdCheck()){
            throw new InvalidPasswordException(BaseErrorCode.INVALID_PASSWORD);
        }

        User user = User.create(userSignUpDto, passwordEncoder);
        userRepository.save(user);
    }
}
