package dobong.life.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.config.SecurityConfig;
import dobong.life.dto.ReviewResDto;
import dobong.life.dto.info.MyPageReviewInfo;
import dobong.life.service.ReviewService;
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

import static dobong.life.controller.dto.TestMyPageControllerResponse.makeTestGetMyPageResDto;
import static dobong.life.controller.dto.TestReviewControllerResponse.makeTestGetReviewResDto;
import static dobong.life.controller.expexted.dto.ReviewResponseDto.expectedGetReviewResDto;
import static dobong.life.controller.expexted.dto.ReviewResponseDto.expectedPostReviewResDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = ReviewController.class,
        excludeAutoConfiguration = { SecurityAutoConfiguration.class, OAuth2ClientAutoConfiguration.class },
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
class ReviewControllerTest {

    @MockitoBean
    ReviewService reviewService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 리뷰저장의_성공응답을_반환한다() throws Exception {
        // given
        MyPageReviewInfo reviewInfo = new MyPageReviewInfo(1L, "test", List.of("good", "best"), "테스트 용으로 좋아요");
        String content = objectMapper.writeValueAsString(reviewInfo);

        // doNothing().when(reviewService).saveReview(any(MyPageReviewInfo.class), any(UserPrincipal.class)); // void 메서드를 테스트할 경우
        given(reviewService.saveReview(any(MyPageReviewInfo.class), any(UserPrincipal.class))).willReturn("리뷰 등록이 완료되었습니다!");

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/dobong/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedPostReviewResDto());
    }

    @Test
    void 특정_도메인의_리뷰들을_반환한다() throws Exception{
        // given

        ReviewResDto Dto = makeTestGetReviewResDto(1L, 1L, 0.0, 0,1L,"test",
                0,"테스트 용 리뷰 내용 저장", List.of("test1", "test2"), true, 1,"good", 3);
        given(reviewService.getStoreReview(anyLong(),anyLong(), any(UserPrincipal.class))).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/reviews/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetReviewResDto());

    }

}