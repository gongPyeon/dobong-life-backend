package dobong.life.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    @Value("${jwt.refresh.expiration}")
    private int REFRESH_TOKEN_MAXAGE;
    private final String REFRESH_TOKEN_KEY = "refresh";
    private final RedisTemplate<String, String> redisTemplate;

    // 리프레시 토큰 저장
    public void saveRefreshToken(Long userId, String refreshToken) {
        redisTemplate.opsForValue().set(
                getRefreshTokenKey(userId),
                refreshToken,
                REFRESH_TOKEN_MAXAGE,
                TimeUnit.SECONDS
        );
    }

    // 리프레시 토큰 조회
    public String getRefreshToken(Long userId) {
        return redisTemplate.opsForValue().get(getRefreshTokenKey(userId));
    }

    // 리프레시 토큰 삭제
    public void deleteRefreshToken(Long userId) {
        redisTemplate.delete(getRefreshTokenKey(userId));
    }

    // 리프레시 토큰 존재 여부 확인
    public boolean hasRefreshToken(Long userId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(getRefreshTokenKey(userId)));
    }

    // 리프레시 토큰 만료 시간 갱신
    public void updateRefreshTokenExpiration(Long userId) {
        String refreshToken = getRefreshToken(userId);
        if (refreshToken != null) {
            redisTemplate.expire(getRefreshTokenKey(userId), REFRESH_TOKEN_MAXAGE, TimeUnit.SECONDS);
        }
    }

    // 리프레시 토큰 키 생성
    private String getRefreshTokenKey(Long userId) {
        return REFRESH_TOKEN_KEY + userId;
    }

}
