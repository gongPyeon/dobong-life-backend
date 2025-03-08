package dobong.life.global.auth.exception;

import dobong.life.global.util.response.status.BaseErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthExceptionHandlerTest {
    @RestController
    @RequestMapping("/test")
    static class AuthExceptionTestController {

        @GetMapping("/inv-jwt")
        public void invJwt() { throw new InvalidJwtException("[ERROR] 유효하지 않은 액세스 토큰입니다"); }
        @GetMapping("/dup-email")
        public void dupEmail() { throw new DuplicateEmailException(BaseErrorCode.DUPLICATED_EMAIL); }
        @GetMapping("/dup-name")
        public void dupName() { throw new DuplicateNicknameException(BaseErrorCode.DUPLICATED_NICKNAME); }
        @GetMapping("/inv-id")
        public void invId() { throw new InvalidIDException(BaseErrorCode.INVALID_ID); }
        @GetMapping("/inv-name")
        public void invName() { throw new InvalidNickNameException(BaseErrorCode.INVALID_ID); }
        @GetMapping("/inv-pwd")
        public void invPwd() { throw new InvalidPasswordException(BaseErrorCode.INVALID_PASSWORD);}
        @GetMapping("/inv-provider")
        public void invProvider() { throw new InvalidProviderException("[ERROR] 지원하지 않은 소셜 플랫폼입니다");}
    }

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new AuthExceptionTestController())
                .setControllerAdvice(new AuthExceptionHandler())
                .build();
    }

    @Nested
    @DisplayName("AuthException 예외가 발생할 시")
    class AuthExceptionTest{

        @Test
        @DisplayName("성공: InvalidJwtException 잡고 처리")
        void throwInvalidJwtEx() throws Exception{
            // given & when
            ResultActions resultActions = mockMvc.perform(get("/test/inv-jwt"));

            // then
            resultActions.andDo(print())
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.message").isString());
        }

        @Test
        @DisplayName("성공: InvalidProviderException 잡고 처리")
        void throwInvalidProviderEx() throws Exception{
            // given & when
            ResultActions resultActions = mockMvc.perform(get("/test/inv-provider"));

            // then
            resultActions.andDo(print())
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.message").isString());
        }

        @Test
        @DisplayName("성공: InvalidIDException 잡고 처리")
        void throwInvalidIDEx() throws Exception{
            // given & when
            ResultActions resultActions = mockMvc.perform(get("/test/inv-id"));

            // then
            resultActions.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").isString());
        }

        @Test
        @DisplayName("성공: InvalidNickNameException 잡고 처리")
        void throwInvalidNameEx() throws Exception{
            // given & when
            ResultActions resultActions = mockMvc.perform(get("/test/inv-name"));

            // then
            resultActions.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").isString());
        }

        @Test
        @DisplayName("성공: InvalidPasswordException 잡고 처리")
        void throwInvalidPwdEx() throws Exception{
            // given & when
            ResultActions resultActions = mockMvc.perform(get("/test/inv-pwd"));

            // then
            resultActions.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").isString());
        }

        @Test
        @DisplayName("성공: DuplicateEmailException 잡고 처리")
        void throwDupEmailEx() throws Exception{
            // given & when
            ResultActions resultActions = mockMvc.perform(get("/test/dup-email"));

            // then
            resultActions.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").isString());
        }

        @Test
        @DisplayName("성공: DuplicateNicknameException 잡고 처리")
        void throwDupNameEx() throws Exception{
            // given & when
            ResultActions resultActions = mockMvc.perform(get("/test/dup-name"));

            // then
            resultActions.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.message").isString());
        }
    }

}