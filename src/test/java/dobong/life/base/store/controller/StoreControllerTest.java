package dobong.life.base.store.controller;

import dobong.life.base.BaseControllerTest;
import dobong.life.base.store.controller.expected.dto.StoreResDto;
import dobong.life.base.store.controller.response.StoreLikeResDTO;
import dobong.life.base.store.controller.response.StoresByIdResDTO;
import dobong.life.base.store.controller.response.StoresByQueryResDTO;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.support.StoreFixture;
import dobong.life.global.util.response.status.BaseCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StoreController.class)
class StoreControllerTest extends BaseControllerTest {
    // dto를 임시로 만든다
    // api 요청을 보낸다
    // 반환되는 dto가 예상한 dto와 맞는지 확인한다
    @Test
    @DisplayName("홈화면 조회:성공")
    void viewStoreListTest() throws Exception{
        //given
        StoresResDTO dto = StoreFixture.storesResDTO();
        given(storeService.getStoreList(anyLong())).willReturn(dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/home")
                        .header(AUTHORIZATION, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(StoreResDto.expectedGetStoresResDTO());
    }

    @Test
    @DisplayName("특정 카테고리에 대한 상점 정보 조회:성공")
    void viewStoreListByCategoryTest() throws Exception{
        //given
        StoresByIdResDTO dto = StoreFixture.storesByIdResDTO();
        given(storeService.getStoreListByCategory(anyString(), anyLong(), anyInt())).willReturn(dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/음식/1")
                        .header(AUTHORIZATION, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(StoreResDto.expectedGetStoresByIdResDTO());
    }

    @Test
    @DisplayName("검색 및 필터적용에 따른 상점 정보 조회:성공")
    void searchStoreListTest() throws Exception{
        //given
        StoresByQueryResDTO dto = StoreFixture.storesByQueryResDTO();
        given(storeService.searchStoreList(anyLong(), anyString(), anyList())).willReturn(dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/search?query=순대&filter=한식&filter=국밥")
                        .header(AUTHORIZATION, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(StoreResDto.expectedGetStoresByQueryResDTO());
    }

    @Test
    @DisplayName("찜목록 조회:성공")
    void viewStoreLikeTest() throws Exception{
        //given
        StoreLikeResDTO dto = StoreFixture.storeLikeResDTO();
        given(storeService.getStoreLikeList(anyLong())).willReturn(dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/like")
                        .header(AUTHORIZATION, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(StoreResDto.expectedGetStoreLikeResDTO());
    }
    @Test
    @DisplayName("상점에 대한 찜 적용:성공")
    void updateStoreLikeTest() throws Exception{
        //given
        String message = BaseCode.SUCCESS_UPDATE_LIKE.getMessage();
        given(storeService.updateStoreLikeByUser(anyLong(), anyLong())).willReturn(message);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/dobong/item/1/like")
                        .header(AUTHORIZATION, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(BaseCode.SUCCESS_UPDATE_LIKE.getMessage()));

    }

}