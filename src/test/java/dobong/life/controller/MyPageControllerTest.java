package dobong.life.controller;

import dobong.life.config.SecurityConfig;
import dobong.life.dto.MyPageResDto;
import dobong.life.dto.MyPageReviewResDto;
import dobong.life.dto.info.StoreBasicInfo;
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

import static dobong.life.controller.expexted.dto.MypageResponseDto.*;
import static dobong.life.controller.dto.TestMyPageControllerResponse.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MyPageController.class)
class MyPageControllerTest extends BaseControllerTest{

    @MockitoBean
    private MyPageService myPageService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void 마이페이지_정보를_반환한다() throws Exception {
        // given
        MyPageResDto Dto = makeTestGetMyPageResDto("test@naver.com", "test", 0, 0,
                0,0,0);

        given(myPageService.getMyPage(anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/my")
                        .with(authentication(auth))
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

        given(myPageService.getMyReview(anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/my/review")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetMyPageReviewResDto());
    }

    @Test
    void 사용자가_좋아요한_리뷰를_반환한다() throws Exception {
        // given
        MyPageReviewResDto Dto = makeTestGetMyPageReviewResDto(1L, "test", List.of("test1", "test2"), "good");

        given(myPageService.getMyReviewLike(anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/my/review/like")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetMyPageReviewResDto());
    }

    @Test
    void 사용자가_좋아요한_도메인을_반환한다() throws Exception {
        // given
        List<StoreBasicInfo> Dto = makeTestGetStoreBasicInfoListDto(1L, "test", "location", "img", true);

        given(myPageService.getMyLike(anyLong(), anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/my/1/like")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetStoreBasicInfoListDto());
    }

}