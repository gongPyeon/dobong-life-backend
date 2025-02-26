package dobong.life.global.auth.service;

import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.auth.support.AuthFixture;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    // TODO: 다시 확인

    @Mock
    CustomUserDetailService customUserDetailService;

    @Mock
    Claims claims;
    @InjectMocks
    AuthenticationService authenticationService;
    Authentication authentication;
    UserPrincipal userPrincipal;
    String email;

    @BeforeEach
    void setUp(){
        authentication = AuthFixture.authentication();
        userPrincipal = (UserPrincipal) authentication.getPrincipal();
        email = userPrincipal.getEmail();

        when(claims.getSubject()).thenReturn(email);
        when(customUserDetailService.loadUserByUsername(email)).thenReturn(userPrincipal);

    }

    @Nested
    @DisplayName("AuthenticationService의 getAuthentication 함수 실행 시")
    class getAuthenticationTest{
        @Test
        @DisplayName("성공")
        void success(){
            // when
            Authentication authentication = authenticationService.getAuthentication(claims);

            // then
            assertNotNull(authentication);
            assertEquals(userPrincipal, authentication.getPrincipal());
            assertTrue(authentication.isAuthenticated());
            verify(customUserDetailService).loadUserByUsername(email);
            assertEquals(UsernamePasswordAuthenticationToken.class, authentication.getClass());
            assertEquals("", authentication.getCredentials());

        }
    }

}