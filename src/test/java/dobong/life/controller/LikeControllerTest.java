package dobong.life.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.config.SecurityConfig;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.service.LikeService;
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

import static dobong.life.controller.expexted.dto.LikeResponseDto.expectedPostLikeDto;
import static dobong.life.controller.expexted.dto.ReviewResponseDto.expectedPostReviewResDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = LikeController.class,
        excludeAutoConfiguration = { SecurityAutoConfiguration.class, OAuth2ClientAutoConfiguration.class },
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
class LikeControllerTest {

    @MockitoBean
    private LikeService likeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 도메인_좋아요의_성공응답을_반환한다() throws Exception {
        // given
        String content = objectMapper.writeValueAsString("");
        given(likeService.updateStoreLikeByUser(any(UserPrincipal.class), anyLong())).willReturn("좋아요 적용이 완료되었습니다!");

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/dobong/like/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedPostLikeDto());
    }

    @Test
    void 리뷰_좋아요의_성공응답을_반환한다() throws Exception {
        // given
        String content = objectMapper.writeValueAsString("");
        given(likeService.updateReviewLikeByUser(any(UserPrincipal.class), anyLong())).willReturn("좋아요 적용이 완료되었습니다!");

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/dobong/like/review/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedPostLikeDto());
    }

}