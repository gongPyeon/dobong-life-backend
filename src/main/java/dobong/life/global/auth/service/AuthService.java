package dobong.life.global.auth.service;

import dobong.life.domain.user.service.query.UserQueryService;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.domain.user.User;
import dobong.life.domain.user.repository.UserRepository;
import dobong.life.domain.like.exception.DuplicateException;
import dobong.life.global.auth.exception.DuplicateEmailException;
import dobong.life.global.auth.exception.DuplicateNicknameException;
import dobong.life.global.auth.exception.InvalidPasswordException;
import dobong.life.global.util.constant.DEFINE;
import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signUp(UserSignUpDto userSignUpDto){
        userQueryService.isDuplicatedID(userSignUpDto.getId());
        userQueryService.isDuplicatedNickName(userSignUpDto.getNickName());
        userQueryService.isInvalidPwdCheck(userSignUpDto);

        userQueryService.save(userSignUpDto, passwordEncoder);

        return DEFINE.SIGN_UP_OK;
    }

    @Transactional
    public void updateNickName(Long userId, String nickName){ // TODO: 소셜로그인에서 이름정보가 필요없는지 (필요없다면 수정)
        userQueryService.isDuplicatedNickName(nickName);

        userQueryService.save(userId, nickName);
    }

    public String checkDupId(String id) {
        userQueryService.isDuplicatedID(id);
        return DEFINE.DUP_ID_OK;
    }

    public String checkDupName(String name) {
        userQueryService.isDuplicatedNickName(name);
        return DEFINE.DUP_NAME_OK;
    }
}
