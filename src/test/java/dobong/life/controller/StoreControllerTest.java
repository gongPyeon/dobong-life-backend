package dobong.life.controller;

import dobong.life.config.SecurityConfig;
import dobong.life.config.TestSecurityConfig;
import dobong.life.config.TestSetUpConfig;
import dobong.life.dto.StoreItemResDto;
import dobong.life.dto.StoresFilterResDto;
import dobong.life.dto.StoresResDto;
import dobong.life.dto.info.ItemInfo;
import dobong.life.entity.*;
import dobong.life.enums.ParentCategoryType;
import dobong.life.enums.Role;
import dobong.life.enums.SocialType;
import dobong.life.service.StoreService;
import dobong.life.service.principal.UserPrincipal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static dobong.life.controller.ResponseDto.*;
import static dobong.life.controller.TestResponse.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = StoreController.class,
        excludeAutoConfiguration = { SecurityAutoConfiguration.class, OAuth2ClientAutoConfiguration.class },
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}

)
@DisplayName("StoreController를_테스트_한다")
class StoreControllerTest {
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

        given(storeService.getStoreList(anyLong(), any(UserPrincipal.class))).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1")
                        .contentType(MediaType.APPLICATION_JSON)
        );

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

        given(storeService.getStoreListByQuery(anyLong(), any(UserPrincipal.class), anyString())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/search?query=순대")
                        .contentType(MediaType.APPLICATION_JSON)
        );

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

        given(storeService.getStoreListByFilter(anyLong(), any(UserPrincipal.class), anyList(), anyList())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/filter?categoryName=분식&subTagId=1")
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

        given(storeService.getStoreListAll(anyLong(), any(UserPrincipal.class), anyLong(), anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/more/1/1")
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

        given(storeService.getStore(anyLong(), any(UserPrincipal.class), anyLong())).willReturn(Dto);

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/dobong/1/item/1")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(expectedGetStoreItemResDto());
    }

}

/**
 *         Category category = new Category(1L, ParentCategoryType.명소);
 *         Domain domain = new Domain(1L, "domain test1", "image", "map", "detail",
 *                 "도봉구", "testItem1, testItem2", category);
 *         SubCategory subCategory = new SubCategory(1L, "한식", "국밥", category);
 *         Tag tag = new Tag(1L, "일상의 행복", category);
 *         SubTag subTag = new SubTag(1L, "소소한 기쁨", tag);
 *         MiddleCategory middleCategory = new MiddleCategory(1L, domain, subCategory, subTag);
 */

//@Test
//void 서브태그_기준_5개_이상의_음식점_리스트정보를_반환하면_예외가_발생한다() throws Exception {// 서브태그_기준_최대_4개의_음식점_리스트정보를_반환한다() -- 서비스 처리
//
//    // given
//    Long[] storeIds = {1L, 2L, 3L, 4L, 5L};
//    String[] storeNames = {"가게1", "가게2", "가게3", "가게4", "가게5"};
//    String[] storeLocations = {"위치1", "위치2", "위치3", "위치4", "위치5"};
//    String[] imgUrls = {"이미지1", "이미지2", "이미지3", "이미지4", "이미지5"};
//    boolean[] storeLikes = {false, false, false, false, false};
//
//    StoresResDto Dto = makeTestGetStoresResDto(1L, 1L, "행복", 1L,
//            "달콤한", storeIds, storeNames, storeLocations, imgUrls, storeLikes);
//
//    given(storeService.getStoreList(eq(1L), any(UserPrincipal.class))).willReturn(Dto);
//
//    //when
//    ResultActions resultActions = mockMvc.perform(
//            MockMvcRequestBuilders.get("/dobong/1")
//                    .contentType(MediaType.APPLICATION_JSON)
//    );
//
//    resultActions.andExpect(result -> {
//        int expectedSize = Dto.getItems().get(0).getStores().size();
//        int actualSize = Integer.parseInt(MockMvcResultMatchers.jsonPath("$.result.items[0].stores.length()").toString());
//
//        Assertions.assertThat(expectedSize).isNotEqualTo(actualSize); // 사이즈가 달라야 성공
//    });

//Long[] storeIds = {1L, 2L, 3L, 4L, 5L};
//String[] storeNames = {"가게1", "순대2", "가게3", "가게4", "순대5"};
//String[] storeLocations = {"위치1", "위치2", "위치3", "위치4", "위치5"};
//String[] imgUrls = {"이미지1", "이미지2", "이미지3", "이미지4", "이미지5"};
//boolean[] storeLikes = {false, false, false, false, false, true};
//}