//package dobong.life.domain.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import dobong.life.base.BaseControllerTest;
//import dobong.life.domain.controller.expexted.dto.LikeResponseDto;
//import dobong.life.domain.like.controller.LikeController;
//import dobong.life.domain.like.service.LikeService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = LikeController.class)
//class LikeControllerTest extends BaseControllerTest {
//
//    @MockitoBean
//    private LikeService likeService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Test
//    void 도메인_좋아요의_성공응답을_반환한다() throws Exception {
//        // given
//        String content = objectMapper.writeValueAsString("");
//        given(likeService.updateStoreLikeByUser(anyLong(), anyLong())).willReturn("좋아요 적용이 완료되었습니다!");
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.post("/dobong/like/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                        //.with(authentication(auth))
//                        .with(csrf()) // 왜 post는 csrf까지 해줘야하는거지?
//        );
//
//        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect(LikeResponseDto.expectedPostLikeDto());
//    }
//
//    @Test
//    void 리뷰_좋아요의_성공응답을_반환한다() throws Exception {
//        // given
//        String content = objectMapper.writeValueAsString("");
//        given(likeService.updateReviewLikeByUser(anyLong(), anyLong())).willReturn("좋아요 적용이 완료되었습니다!");
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.post("/dobong/like/review/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                        //.with(authentication(auth))
//                        .with(csrf())
//        );
//
//        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect(LikeResponseDto.expectedPostLikeDto());
//    }
//
//}