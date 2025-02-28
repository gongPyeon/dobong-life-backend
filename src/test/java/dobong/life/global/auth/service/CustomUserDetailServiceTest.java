package dobong.life.global.auth.service;

import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.dto.RegisterUserCommand;
import dobong.life.global.auth.enums.Role;
import dobong.life.global.auth.service.principal.UserPrincipal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailServiceTest {

    @Mock
    UserService userService;

    @InjectMocks
    CustomUserDetailService customUserDetailService;
    @Nested
    @DisplayName("loadUserByUsername 호출 시")
    class loadUserByUsernameTest {

        @Test
        @DisplayName("성공")
        void success() {
            // given
            String email = "honggil@konkuk.ac.kr";
            RegisterResponse registerResponse = new RegisterResponse(1L, "홍길동", "honggil@konkuk.ac.kr", null, null, Role.USER_OAUTH2);

            // when
            when(userService.getOrRegisterUser(any(RegisterUserCommand.class))).thenReturn(registerResponse);
            UserPrincipal result = (UserPrincipal) customUserDetailService.loadUserByUsername(email);
            // TODO: loadUser, loadUserByUsername 부분 확인

            // then
            assertNotNull(result);
            assertEquals(registerResponse.userId(), result.getRegisterResponse().userId());
            assertEquals(registerResponse.email(), result.getRegisterResponse().email());
            assertEquals(registerResponse.name(), result.getRegisterResponse().name());
        }

    }

}