package dobong.life.global.auth.service;

import dobong.life.domain.user.User;
import dobong.life.domain.user.exception.UserNotFoundException;
import dobong.life.domain.user.repository.UserRepository;
import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.dto.RegisterUserCommand;
import dobong.life.global.auth.enums.Role;
import dobong.life.global.auth.exception.InvalidPasswordException;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.auth.support.AuthFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Nested
    @DisplayName("getOrRegisterUser 호출 시")
    class GetOrRegisterUserTest {

        RegisterUserCommand registerUserCommand = AuthFixture.registerUserCommand();

        @Test
        @DisplayName("사용자 생성")
        void getUserWhenUserNotFound() {
            //given
            given(userRepository.findByProviderIdAndProviderType(any(), any())).willReturn(
                    Optional.empty());

            //when
            userService.getOrRegisterUser(registerUserCommand);

            //then
            then(userRepository).should(times(1)).save(any());

        }

        @Test
        @DisplayName("사용자 조회")
        void getUserWhenUserExists() {
            //given
            User user = AuthFixture.user();
            given(userRepository.findByProviderIdAndProviderType(any(), any())).willReturn(
                    Optional.ofNullable(user));

            //when
            userService.getOrRegisterUser(registerUserCommand);

            //then
            then(userRepository).should(times(0)).save(any());
        }
    }

    @Nested
    @DisplayName("getRegisterUser 호출 시")
    class GetRegisterUserTest {

        String email = "honggil@konkuk.ac.kr";

        @Test
        @DisplayName("성공: 사용자 조회")
        void getUserWhenUserExists() {
            //given
            given(userRepository.findByEmail(any())).willReturn(
                    Optional.empty());

            //when
            userService.getRegisterUser(email);

            //then
            then(userRepository).should(times(0)).save(any());

        }

        @Test
        @DisplayName("예외: 사용자 없음")
        void getUserWhenUserNotFound() {
            //given
            User user = AuthFixture.user();
            given(userRepository.findByEmail(any())).willReturn(
                    Optional.ofNullable(user));

            // when & then
            assertThrows(UserNotFoundException.class, () -> userService.getRegisterUser(email));
        }
    }

}