package dobong.life.base.user.service.query;

import dobong.life.base.user.User;
import dobong.life.base.user.exception.UserNotFoundException;
import dobong.life.base.user.repository.UserRepository;
import dobong.life.global.auth.controller.request.UserSignUpDto;
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
public class UserQueryService {
    private final UserRepository userRepository;

    public User getUserById(long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(BaseErrorCode.USER_NOT_FOUND,
                        "[ERROR] "+userId+"에 해당하는 사용자를 찾을 수 없습니다"));
    }

    public void isInvalidPwdCheck(UserSignUpDto userSignUpDto) {
        if(!userSignUpDto.getPwd().equals(userSignUpDto.getPwdCheck())){
            throw new InvalidPasswordException(BaseErrorCode.INVALID_PASSWORD);
        }
    }

    public void isDuplicatedNickName(String nickName) {
        userRepository.findByNickName(nickName)
                .ifPresent(u -> {
                    throw new DuplicateNicknameException(BaseErrorCode.DUPLICATED_NICKNAME);
                });
    }

    public void isDuplicatedID(String email) {
        userRepository.findByEmail(email)
                .ifPresent(u -> {
                    throw new DuplicateEmailException(BaseErrorCode.DUPLICATED_EMAIL);
                });
    }

    @Transactional
    public void save(UserSignUpDto userSignUpDto, PasswordEncoder passwordEncoder) {
        User user = User.create(userSignUpDto, passwordEncoder);
        userRepository.save(user);
    }

    public void save(Long userId, String nickName){
        User user = getUserById(userId);
        user.updateNickName(nickName);
    }
}
