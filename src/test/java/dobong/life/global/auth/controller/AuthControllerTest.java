package dobong.life.global.auth.controller;

import dobong.life.base.BaseControllerTest;
import dobong.life.global.auth.controller.expected.dto.AuthResponseDto;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.service.AuthService;
import dobong.life.global.auth.support.AuthFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest extends BaseControllerTest {

    @MockitoBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("회원가입 시")
    class SignUp{
        @Test
        @DisplayName("성공")
        void success() throws Exception {

            // given
            UserSignUpDto result = AuthFixture.userSignUpDtoById("konkuk1234");
            String content = objectMapper.writeValueAsString(result);
            given(authService.signUp(any(UserSignUpDto.class))).willReturn("회원가입에 성공했습니다.");

            //when
            ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post("/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content)
                            .with(csrf())
            );

            //then
            resultActions.andExpect(status().isOk())
                    .andExpect(AuthResponseDto.expectedPostAuthDto());
        }

        @Nested
        @DisplayName("사용자 ID 검증 시")
        class UserIdTes{

            @ParameterizedTest
            @CsvSource({
                    "member", "hello0331"
            })
            @DisplayName("성공")
            void success(String validUsrId) throws Exception {

                // given
                UserSignUpDto result = AuthFixture.userSignUpDtoById(validUsrId);
                String content = objectMapper.writeValueAsString(result);

                // when
                ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

                // then
                resultActions.andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").isString());

            }

            @ParameterizedTest
            @CsvSource({
                    "000000000000000000000"
            })
            @DisplayName("유효하지 않은 아이디 (최소 1개 이상, 최대 20자가 아닐 경우)")
            void throwExceptionWhenInvalidLength(String invalidUsrId) throws Exception {

                // given
                UserSignUpDto result = AuthFixture.userSignUpDtoById(invalidUsrId);
                String content = objectMapper.writeValueAsString(result);

                // when
                ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

                resultActions.andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").isString());
            }

            // 유효하지 않은 아이디 (알파벳 또는 숫자가 아닐경우)
            // 아이디 중복확인
        }

        @Nested
        @DisplayName("사용자 Pwd 검증 시")
        class UserPwdTes{

            @ParameterizedTest
            @CsvSource({
                    "Oauth2**", "hello30!a"
            })
            @DisplayName("성공")
            void success(String validUsrPwd) throws Exception {

                // given
                UserSignUpDto result = AuthFixture.userSignUpDtoByPwd(validUsrPwd);
                String content = objectMapper.writeValueAsString(result);

                // when
                ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

                // then
                resultActions.andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").isString());

            }

            @ParameterizedTest
            @CsvSource({
                    "abc!00", "abc!0000000000000000000000000000000000000"
            })
            @DisplayName("유효하지 않은 비밀번호 (최소 8개 이상, 최대 30자가 아닐 경우)")
            void throwExceptionWhenInvalidLength(String invalidUsrPwd) throws Exception {

                // given
                UserSignUpDto result = AuthFixture.userSignUpDtoByPwd(invalidUsrPwd);
                String content = objectMapper.writeValueAsString(result);

                // when
                ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

                // then
                resultActions.andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").isString());
            }

            //TODO: " Oauth2**", " Oauth2** ", 같이 앞뒤 공백같은 경우 프론트에서 막은 후 보내줘야한다
            @ParameterizedTest
            @CsvSource({
                    "Oauth2****안녕", "!!!!!!!!!", "abcabcabc",
                    "123123123", "!a!a!a!a", "!1!1!1!1", "a1a1a1a1", "Oaut h2**"
            })
            @DisplayName("유효하지 않은 비밀번호 (알파벳, 숫자, 특수문자을 모두 포함하지 않거나 다른문자를 포함시킬경우)")
            void throwExceptionWhenInvalidFormat(String invalidUsrPwd) throws Exception {
                // given
                UserSignUpDto result = AuthFixture.userSignUpDtoByPwd(invalidUsrPwd);
                String content = objectMapper.writeValueAsString(result);

                // when
                ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

                // then
                resultActions.andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").isString());
            }
            // 비밀번호 재확인
        }

        @Nested
        @DisplayName("사용자 NickName 검증 시")
        class UserNickNameTes{

            @ParameterizedTest
            @CsvSource({
                    "user0225", "김길동"
            })
            @DisplayName("성공")
            void success(String validUsrName) throws Exception {

                // given
                UserSignUpDto result = AuthFixture.userSignUpDtoByName(validUsrName);
                String content = objectMapper.writeValueAsString(result);

                // when
                ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

                // then
                resultActions.andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.message").isString());

            }

            @ParameterizedTest
            @CsvSource({
                    "abc0000000000000000000000000000000000000"
            })
            @DisplayName("유효하지 않은 닉네임 (최소 1개 이상, 최대 30자가 아닐 경우)")
            void throwExceptionWhenInvalidLength(String invalidUsrName) throws Exception {

                // given
                UserSignUpDto result = AuthFixture.userSignUpDtoByName(invalidUsrName);
                String content = objectMapper.writeValueAsString(result);

                // when
                ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                                .with(csrf())
                );

                // then
                resultActions.andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.message").isString());
            }

            // 유효하지 않은 닉네임 (알파벳, 숫자, 한글 외 다른 문자를 포함시킬경우)
            // 닉네임 중복 확인
        }
    }

}