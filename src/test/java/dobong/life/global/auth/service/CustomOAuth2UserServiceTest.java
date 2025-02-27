package dobong.life.global.auth.service;

import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.dto.RegisterUserCommand;
import dobong.life.global.auth.enums.Role;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.auth.support.AuthFixture;
import dobong.life.global.util.constant.DEFINE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomOAuth2UserServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private CustomOAuth2UserService customOAuth2UserService;

    OAuth2UserRequest oAuth2UserRequest;
    OAuth2User oAuth2User;
    @BeforeEach
    void setUp(){
        oAuth2UserRequest = AuthFixture.oAuth2UserRequest();
        oAuth2User = AuthFixture.oAuth2User();
    }
    @Nested
    @DisplayName("Oauth2 loadUser 호출 시")
    class loadUserTest {

        @Test
        @DisplayName("성공")
        void success() {
            // given
            RegisterResponse registerResponse = new RegisterResponse(1L, "홍길동", "honggil@konkuk.ac.kr", null, null, Role.USER_OAUTH2);
            UserPrincipal userPrincipal = new UserPrincipal(registerResponse, null);
            when(userService.getOrRegisterUser(any(RegisterUserCommand.class))).thenReturn(registerResponse);
            when(customOAuth2UserService.loadUser(oAuth2UserRequest))
                    .thenReturn(userPrincipal);

            // when
            UserPrincipal result = (UserPrincipal) customOAuth2UserService.loadUser(oAuth2UserRequest);

            // then
            assertNotNull(result);
            assertEquals(registerResponse.userId(), result.getRegisterResponse().userId());
            assertEquals(registerResponse.email(), result.getRegisterResponse().email());
            assertEquals(registerResponse.name(), result.getRegisterResponse().name());
        }
    }

}