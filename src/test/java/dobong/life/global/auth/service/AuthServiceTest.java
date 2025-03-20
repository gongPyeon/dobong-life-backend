package dobong.life.global.auth.service;

import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.exception.DuplicateEmailException;
import dobong.life.global.auth.exception.DuplicateNicknameException;
import dobong.life.global.auth.exception.InvalidPasswordException;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.auth.support.AuthFixture;
import dobong.life.global.util.response.status.BaseCode;
import dobong.life.global.util.response.status.BaseErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserQueryService userQueryService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;
    private UserSignUpDto userSignUpDto;

    @BeforeEach
    void setUp(){
        userSignUpDto = AuthFixture.userSignUpDto();
    }
    @Nested
    @DisplayName("회원가입 시")
    class SignUpTest{

        @Test
        @DisplayName("성공")
        void success(){
            // given
            doNothing().when(userQueryService).isDuplicatedID(userSignUpDto.getId());
            doNothing().when(userQueryService).isDuplicatedNickName(userSignUpDto.getNickname());
            doNothing().when(userQueryService).isInvalidPwdCheck(userSignUpDto);
            doNothing().when(userQueryService).save(userSignUpDto, passwordEncoder);

            // when
            String result = authService.signUp(userSignUpDto);

            // then
            assertEquals(BaseCode.SUCCESS_SIGN_UP.getMessage(), result);
            verify(userQueryService).isDuplicatedID(userSignUpDto.getId());
            verify(userQueryService).isDuplicatedNickName(userSignUpDto.getNickname());
            verify(userQueryService).isInvalidPwdCheck(userSignUpDto);
            verify(userQueryService).save(userSignUpDto, passwordEncoder);
        }

        @Test
        @DisplayName("예외: 중복 아이디(이메일)")
        void throwDupEmailException(){
            // when
            doThrow(new DuplicateEmailException(BaseErrorCode.DUPLICATED_EMAIL))
                    .when(userQueryService)
                    .isDuplicatedID(userSignUpDto.getId());

            // then
            assertThrows(DuplicateEmailException.class, () -> authService.signUp(userSignUpDto));
            verify(userQueryService).isDuplicatedID(userSignUpDto.getId());
            verify(userQueryService, never()).isDuplicatedNickName(anyString());
            verify(userQueryService, never()).isInvalidPwdCheck(any());
            verify(userQueryService, never()).save(any(UserSignUpDto.class), any());
        }


        @Test
        @DisplayName("예외: 중복 닉네임")
        void throwDupNickNameException(){
            // when
            doNothing().when(userQueryService).isDuplicatedID(userSignUpDto.getId());
            doThrow(new DuplicateNicknameException(BaseErrorCode.DUPLICATED_NICKNAME))
                    .when(userQueryService)
                    .isDuplicatedNickName(userSignUpDto.getNickname());

            // then
            assertThrows(DuplicateNicknameException.class, () -> authService.signUp(userSignUpDto));
            verify(userQueryService).isDuplicatedID(userSignUpDto.getId());
            verify(userQueryService).isDuplicatedNickName(userSignUpDto.getNickname());
            verify(userQueryService, never()).isInvalidPwdCheck(any());
            verify(userQueryService, never()).save(any(UserSignUpDto.class), any());
        }

        @Test
        @DisplayName("예외: 패스워드 불일치")
        void throwInvalidPwdException(){
            // when
            doNothing().when(userQueryService).isDuplicatedID(userSignUpDto.getId());
            doNothing().when(userQueryService).isDuplicatedNickName(userSignUpDto.getNickname());
            doThrow(new InvalidPasswordException(BaseErrorCode.INVALID_PASSWORD)).when(userQueryService).isInvalidPwdCheck(userSignUpDto);

            // then
            assertThrows(InvalidPasswordException.class, () -> authService.signUp(userSignUpDto));
            verify(userQueryService).isDuplicatedID(userSignUpDto.getId());
            verify(userQueryService).isDuplicatedNickName(userSignUpDto.getNickname());
            verify(userQueryService).isInvalidPwdCheck(userSignUpDto);
            verify(userQueryService, never()).save(any(UserSignUpDto.class), any());
        }

    }

}