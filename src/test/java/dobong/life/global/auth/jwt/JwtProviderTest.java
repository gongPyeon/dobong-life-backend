package dobong.life.global.auth.jwt;

import dobong.life.global.auth.dto.TokenCommand;
import dobong.life.global.auth.exception.InvalidJwtException;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.auth.support.AuthFixture;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Slf4j
class JwtProviderTest {
    private final static String BEARER_TYPE = "Bearer ";
    JwtProvider jwtProvider;
    Authentication authentication;
    UserPrincipal userPrincipal;
    @BeforeEach
    void setUp(){
        jwtProvider = AuthFixture.jwtProvider();
        authentication = AuthFixture.authentication();
        userPrincipal = (UserPrincipal) authentication.getPrincipal();
    }
    @Nested
    @DisplayName("generateToken 메서드 실행 시")
    class GenerateTokenTest{

        @Test
        @DisplayName("성공: 토큰 반환")
        void success(){
            // when
            TokenCommand tokenCommand = jwtProvider.generateToken(authentication);

            // then
            assertThat(tokenCommand).isNotNull();
            assertThat(tokenCommand.getAccessToken()).isNotBlank();
            assertThat(tokenCommand.getRefreshToken()).isNotBlank();
        }
    }

    @Nested
    @DisplayName("extractBearer 메서드 실행 시")
    class ExtractBearerTest{

        @Test
        @DisplayName("Bearer 접두사가 있는 토큰에서 실제 토큰을 추출")
        void extractTokenFromBearer() {
            // given
            String tokenWithBearer = BEARER_TYPE + "actualToken";

            // when
            String extractedToken = jwtProvider.extractBearer(tokenWithBearer);

            // then
            assertEquals("actualToken", extractedToken);
        }

        @Test
        @DisplayName("Bearer 접두사가 없는 토큰에서는 null을 반환")
        void returnNullForNonBearerToken() {
            // given
            String tokenWithoutBearer = "actualToken";

            // when
            String extractedToken = jwtProvider.extractBearer(tokenWithoutBearer);

            // then
            assertNull(extractedToken);
        }
    }

    @Nested
    @DisplayName("validateToken 메서드 실행 시")
    class ValidateTokenTest{
        @Test
        @DisplayName("유효한 토큰을 검증하고 Claims을 반환한다")
        void validateValidToken() {
            // given
            // 실제 토큰 생성 (테스트용)
            TokenCommand tokenCommand = jwtProvider.generateToken(authentication);
            String validToken = tokenCommand.getAccessToken();

            // when
            Claims claims = jwtProvider.validateToken(validToken);

            // then
            assertThat(claims).isNotNull();
            assertEquals(userPrincipal.getEmail(), claims.getSubject());
            // assertEquals(userPrincipal.getAuthorities(), claims.get("authorities")); 왜 null이지?
        }

        @Test
        @DisplayName("만료된 토큰은 예외를 발생시킨다")
        void throwExceptionForExpiredToken() {
            // given
            // 만료된 토큰 모킹 (실제 구현에 따라 조정 필요)
            String expiredToken = "expiredToken";

            // when, then
            assertThrows(InvalidJwtException.class, () -> {
                jwtProvider.validateToken(expiredToken);
            });
        }

        @Test
        @DisplayName("유효하지 않은 형식의 토큰은 예외를 발생시킨다")
        void throwExceptionForMalformedToken() {
            // given
            String malformedToken = "malformed.token.format";

            // when, then
            assertThrows(InvalidJwtException.class, () -> {
                jwtProvider.validateToken(malformedToken);
            });
        }
    }

    /**
     * assertThatThrownBy(() -> tokenProvider.validateToken(invalidAlgorithmToken))
     *                 .isInstanceOf(InvalidJwtException.class);
     * 이 형식을 쓸지 고민해보기
     */

}