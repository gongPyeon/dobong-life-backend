package dobong.life.controller;

import dobong.life.config.SecurityConfig;
import dobong.life.dto.MyPageResDto;
import dobong.life.dto.MyPageReviewResDto;
import dobong.life.service.MyPageService;
import dobong.life.service.principal.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static dobong.life.controller.MypageResponseDto.expectedGetMyPageResDto;
import static dobong.life.controller.MypageResponseDto.expectedGetMyPageReviewResDto;
import static dobong.life.controller.TestMyPageControllerResponse.makeTestGetMyPageResDto;
import static dobong.life.controller.TestMyPageControllerResponse.makeTestGetMyPageReviewResDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = MyPageController.class,
        excludeAutoConfiguration = { SecurityAutoConfiguration.class, OAuth2ClientAutoConfiguration.class },
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
class MyPageControllerTest {

    @MockitoBean
    private MyPageService myPageService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 마이페이지_정보를_반환한다() throws Exception {
        // given
        MyPageResDto Dto = makeTestGetMyPageResDto("test@naver.com", "test", 0, 0,
                0,0,0);

        given(myPageService.getMyPage(any(UserPrincipal.class))).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/my")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetMyPageResDto());
    }

    @Test
    void 사용자가_작성한_리뷰를_반환한다() throws Exception {
        // given
        MyPageReviewResDto Dto = makeTestGetMyPageReviewResDto(1L, "test", List.of("test1", "test2"), "good");

        given(myPageService.getMyReview(any(UserPrincipal.class))).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/my/review")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetMyPageReviewResDto());
    }

}