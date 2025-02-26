package dobong.life.global.auth.controller;

import dobong.life.base.BaseControllerTest;
import dobong.life.domain.controller.dto.TestStoreControllerResponse;
import dobong.life.domain.controller.expexted.dto.LikeResponseDto;
import dobong.life.domain.controller.expexted.dto.StoreResponseDto;
import dobong.life.domain.store.controller.StoreController;
import dobong.life.domain.store.controller.response.StoresResDto;
import dobong.life.domain.store.service.StoreService;
import dobong.life.global.auth.controller.expected.dto.AuthResponseDto;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.service.AuthService;
import dobong.life.global.auth.support.AuthFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@DisplayName("AuthController를_테스트_한다")
@Slf4j
class AuthControllerTest extends BaseControllerTest {

    @MockitoBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원가입 성공")
    void signUp_success() throws Exception {

        // given
        UserSignUpDto result = AuthFixture.userSignUpDtoById("konkuk1234");
        log.info("UserSignUpDto = {}", result);
        String content = objectMapper.writeValueAsString(result);
        log.info("content = {}", content);
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

    // 여기에 DTO Valid 부분을 작성

}