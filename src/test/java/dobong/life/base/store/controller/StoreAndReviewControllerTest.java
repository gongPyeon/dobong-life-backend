package dobong.life.base.store.controller;

import dobong.life.base.BaseControllerTest;
import dobong.life.base.store.controller.expected.dto.StoreResDto;
import dobong.life.base.store.controller.response.StoreResDTO;
import dobong.life.base.store.service.StoreAndReviewService;
import dobong.life.base.store.support.StoreFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StoreAndReviewController.class)
class StoreAndReviewControllerTest extends BaseControllerTest {
    @Test
    @DisplayName("상점 상세페이지 조회:성공")
    void viewStoreListTest() throws Exception{
        //given
        StoreResDTO dto = StoreFixture.storeResDTO();
        given(storeAndReviewService.getStore(anyLong(), anyLong())).willReturn(dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/item/1")
                        .header(AUTHORIZATION, accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(StoreResDto.expectedGetStoreResDTO());
    }

}