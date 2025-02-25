//package dobong.life.domain.controller;
//
//import dobong.life.domain.controller.dto.TestMyPageControllerResponse;
//import dobong.life.domain.controller.expexted.dto.MypageResponseDto;
//import dobong.life.domain.user.controller.MyPageController;
//import dobong.life.domain.user.controller.response.MyPageResDto;
//import dobong.life.domain.user.controller.response.MyPageReviewResDto;
//import dobong.life.domain.store.dto.StoreBasicInfo;
//import dobong.life.domain.user.service.MyPageService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = MyPageController.class)
//class MyPageControllerTest extends BaseControllerTest{
//
//    @MockitoBean
//    private MyPageService myPageService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void 마이페이지_정보를_반환한다() throws Exception {
//        // given
//        MyPageResDto Dto = TestMyPageControllerResponse.makeTestGetMyPageResDto("test@naver.com", "test", 0, 0,
//                0,0,0);
//
//        given(myPageService.getMyPage(anyLong())).willReturn(Dto);
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/dobong/my")
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect(MypageResponseDto.expectedGetMyPageResDto());
//    }
//
//    @Test
//    void 사용자가_작성한_리뷰를_반환한다() throws Exception {
//        // given
//        MyPageReviewResDto Dto = TestMyPageControllerResponse.makeTestGetMyPageReviewResDto(1L, "test", List.of("test1", "test2"), "good");
//
//        given(myPageService.getMyReview(anyLong())).willReturn(Dto);
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/dobong/my/review")
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect(MypageResponseDto.expectedGetMyPageReviewResDto());
//    }
//
//    @Test
//    void 사용자가_좋아요한_리뷰를_반환한다() throws Exception {
//        // given
//        MyPageReviewResDto Dto = TestMyPageControllerResponse.makeTestGetMyPageReviewResDto(1L, "test", List.of("test1", "test2"), "good");
//
//        given(myPageService.getMyReviewLike(anyLong())).willReturn(Dto);
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/dobong/my/review/like")
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect(MypageResponseDto.expectedGetMyPageReviewResDto());
//    }
//
//    @Test
//    void 사용자가_좋아요한_도메인을_반환한다() throws Exception {
//        // given
//        List<StoreBasicInfo> Dto = TestMyPageControllerResponse.makeTestGetStoreBasicInfoListDto(1L, "test", "location", "img", true);
//
//        given(myPageService.getMyLike(anyLong(), anyLong())).willReturn(Dto);
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/dobong/my/1/like")
//                        .with(authentication(auth))
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect(MypageResponseDto.expectedGetStoreBasicInfoListDto());
//    }
//
//}