package dobong.life.global.util.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisUtil {

    @Value("${jwt.refresh.expiration}")
    private int REFRESH_TOKEN_MAXAGE;
    private final String REFRESH_TOKEN_KEY = "refresh";
    private final RedisTemplate<String, String> redisTemplate;

    // 리프레시 토큰 저장
    public void saveRefreshToken(String email, String refreshToken) {
        redisTemplate.opsForValue().set(
                getRefreshTokenKey(email),
                refreshToken,
                REFRESH_TOKEN_MAXAGE,
                TimeUnit.SECONDS
        );
    }

    // 리프레시 토큰 조회
    public String getRefreshToken(String email) {
        log.info("refreshToken email : ", email);
        log.info("refreshTokenKey : ", getRefreshTokenKey(email));
        return redisTemplate.opsForValue().get(getRefreshTokenKey(email));
    }

    // 리프레시 토큰 삭제
    public void deleteRefreshToken(String email) {
        redisTemplate.delete(getRefreshTokenKey(email));
    }

    // 리프레시 토큰 존재 여부 확인
    public boolean hasRefreshToken(String email) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(getRefreshTokenKey(email)));
    }

    // 리프레시 토큰 만료 시간 갱신
    public void updateRefreshTokenExpiration(String email) {
        String refreshToken = getRefreshToken(email);
        if (refreshToken != null) {
            redisTemplate.expire(getRefreshTokenKey(email), REFRESH_TOKEN_MAXAGE, TimeUnit.SECONDS);
        }
    }

    // 리프레시 토큰 키 생성
    private String getRefreshTokenKey(String email) {
        return REFRESH_TOKEN_KEY + email;
    }

}
