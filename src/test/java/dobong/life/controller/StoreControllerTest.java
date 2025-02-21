package dobong.life.controller;

import dobong.life.domain.store.controller.StoreController;
import dobong.life.domain.store.controller.response.StoreItemResDto;
import dobong.life.domain.store.controller.response.StoresFilterResDto;
import dobong.life.domain.store.controller.response.StoresResDto;
import dobong.life.domain.store.service.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static dobong.life.controller.expexted.dto.StoreResponseDto.*;
import static dobong.life.controller.dto.TestStoreControllerResponse.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(
//        controllers = StoreController.class
//        excludeAutoConfiguration = { SecurityAutoConfiguration.class, OAuth2ClientAutoConfiguration.class },
//        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
//
//)
@WebMvcTest(controllers = StoreController.class)
@DisplayName("StoreController를_테스트_한다")
class StoreControllerTest extends BaseControllerTest{
    // 올바른 HTTP 응답 코드와 DTO가 반환되는지 확인

    @MockitoBean
    private StoreService storeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 홈화면_도메인_리스트정보를_반환한다() throws Exception {

        // given
        Long[] storeIds = {1L, 2L, 3L, 4L};
        String[] storeNames = {"가게1", "가게2", "가게3", "가게4"};
        String[] storeLocations = {"위치1", "위치2", "위치3", "위치4"};
        String[] imgUrls = {"이미지1", "이미지2", "이미지3", "이미지4"};
        boolean[] storeLikes = {false, false, false, false, false};

        StoresResDto Dto = makeTestGetStoresResDto(1L, 1L, "행복", 1L,
                "달콤한", storeIds, storeNames, storeLocations, imgUrls, storeLikes);

        given(storeService.getStoreList(anyLong(), anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform( // 여기서 id를 설정해야해
                MockMvcRequestBuilders.get("/dobong/1")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetStoresResDto());
    }

    @Test
    void 검색어에_따른_도메인_정보를_반환한다() throws Exception {

        // given
        Long[] storeIds = {1L, 2L};
        String[] storeNames = {"순대1", "순대2"};
        String[] storeLocations = {"위치1", "위치2"};
        String[] imgUrls = {"이미지1", "이미지2"};
        boolean[] storeLikes = {false, true};

        StoresResDto Dto = makeTestGetStoresResDto(1L, 1L, "행복", 1L,
                "달콤한", storeIds, storeNames, storeLocations, imgUrls, storeLikes);

        given(storeService.getStoreListByQuery(anyLong(), anyLong(), anyString())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/search?query=순대")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetStoresResDtoByQuery());
    }

    @Test
    void 필터에_따른_도메인_정보를_반환한다() throws Exception {

        // given
        Long[] storeIds = {1L, 2L};
        String[] storeNames = {"순대1", "순대2"};
        String[] storeLocations = {"위치1", "위치2"};
        String[] categoryNames = {"분식"};
        String[] subTagNames = {"맛집"};
        String[] imgUrls = {"이미지1", "이미지2"};
        boolean[] storeLikes = {false, true};

        StoresFilterResDto Dto = makeTestGetStoresFilterResDto(1L, categoryNames, subTagNames, storeIds, storeNames, storeLocations, imgUrls, storeLikes);

        given(storeService.getStoreListByFilter(anyLong(), anyLong(), anyList(), anyList())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/filter?categoryName=분식&subTagId=1")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetStoresFilterResDto());
    }

    @Test
    void 더보기_버튼을_눌렀을_경우_전체_도메인_리스트정보를_반환한다() throws Exception {

        // given
        Long[] storeIds = {1L, 2L, 3L, 4L};
        String[] storeNames = {"가게1", "가게2", "가게3", "가게4"};
        String[] storeLocations = {"위치1", "위치2", "위치3", "위치4"};
        String[] imgUrls = {"이미지1", "이미지2", "이미지3", "이미지4"};
        boolean[] storeLikes = {false, false, false, false, false};

        StoresResDto Dto = makeTestGetStoresResDto(1L, 1L, "행복", 1L,
                "달콤한", storeIds, storeNames, storeLocations, imgUrls, storeLikes);

        given(storeService.getStoreListAll(anyLong(), anyLong(), anyLong(), anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/more/1/1")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetStoresResDto());
    }

    @Test
    void 특정_도메인의_상세정보를_반환한다() throws Exception {

        // given
        String[] storeMenus = {"메뉴1", "메뉴2", "메뉴3"};
        String[] storeKeywords = {"키워드1", "키워드2", "키워드3"};

        StoreItemResDto Dto = makeTestGetStoreItemResDto(1L, 1L, "가게1", "위치1",
                "이미지1", false, "서브카테고리명1",
                "상세주소1", storeMenus, storeKeywords);

        given(storeService.getStore(anyLong(), anyLong(), anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/item/1")
                        .with(authentication(auth))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetStoreItemResDto());
    }

}