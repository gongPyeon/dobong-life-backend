package dobong.life.base.review.controller;

import dobong.life.base.BaseControllerTest;
import dobong.life.base.review.controller.request.ReviewReqDTO;
import dobong.life.base.review.support.ReviewFixture;
import dobong.life.base.store.controller.StoreAndReviewController;
import dobong.life.base.store.controller.expected.dto.StoreResDto;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.support.StoreFixture;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.support.AuthFixture;
import dobong.life.global.util.response.status.BaseCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReviewController.class)
class ReviewControllerTest extends BaseControllerTest {
    @Test
    @DisplayName("리뷰등록 :성공")
    void createMyReviewTest() throws Exception{
        //given
        ReviewReqDTO result = ReviewFixture.reviewReqDTO();
        String content = objectMapper.writeValueAsString(result);

        String message = BaseCode.SUCCESS_SAVE_REVIEW.getMessage();
        given(reviewService.saveReview(any(), anyLong())).willReturn(message);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/dobong/review")
                        .header(AUTHORIZATION, accessToken)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(BaseCode.SUCCESS_SAVE_REVIEW.getMessage()));
    }
}