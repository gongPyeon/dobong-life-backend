package dobong.life.global.auth.service;

import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.exception.*;
import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.util.redis.RedisUtil;
import dobong.life.global.util.response.status.BaseCode;
import dobong.life.global.util.response.status.BaseErrorCode;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisUtil redisUtil;

    @Transactional
    public String signUp(UserSignUpDto userSignUpDto){
        userQueryService.isDuplicatedID(userSignUpDto.getId());
        userQueryService.isDuplicatedNickName(userSignUpDto.getNickname());
        userQueryService.isInvalidPwdCheck(userSignUpDto);

        userQueryService.save(userSignUpDto, passwordEncoder);

        return BaseCode.SUCCESS_SIGN_UP.getMessage();
    }

    @Transactional
    public void updateNickName(Long userId, String nickName){ // TODO: 소셜로그인에서 이름정보가 필요없는지 (필요없다면 수정)
        userQueryService.isDuplicatedNickName(nickName);

        userQueryService.save(userId, nickName);
    }

    public String checkDupId(String id) {
        userQueryService.isDuplicatedID(id);
        return BaseCode.DUP_ID_OK.getMessage();
    }

    public String checkDupNickName(String nickname) {
        userQueryService.isDuplicatedNickName(nickname);
        return BaseCode.DUP_NAME_OK.getMessage();
    }


    // TODO: 예외 핸들링 수정 & DTO 설정
    public String reissueToken(String refreshToken) {
        Claims claims = jwtProvider.validateToken(refreshToken);
        String email = claims.getSubject();

        try {
            String refreshTokenByRedis = redisUtil.getRefreshToken(email);
            if(!refreshTokenByRedis.matches(refreshToken)){
                throw new RedisException(BaseErrorCode.INVALID_REISSUE, "[ERROR] Redis refreshToken 정보와 다릅니다.");
            }
        }catch (NullPointerException e){
            throw new RedisException(BaseErrorCode.INVALID_REISSUE,"[ERROR] Redis에 email에 맞는 RefreshToken이 저장되어있지 않습니다.");
        }

        String accessToken = jwtProvider.generateTokenFromRefreshToken(refreshToken);
        return accessToken;
    }
}
