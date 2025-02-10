package dobong.life.controller;

import dobong.life.config.SecurityConfig;
import dobong.life.dto.MyPageResDto;
import dobong.life.dto.StoresResDto;
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

import static dobong.life.controller.StoreResponseDto.expectedGetStoresResDto;
import static dobong.life.controller.TestStoreControllerResponse.makeTestGetStoresResDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = MyPageController.class,
        excludeAutoConfiguration = { SecurityAutoConfiguration.class, OAuth2ClientAutoConfiguration.class },
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)}
)
class MyPageControllerTest {

    @MockitoBean
    private MyPageService myPageService;

    @Autowired
    MockMvc mockMvc;

//    @Test
//    void 마이페이지_정보를_반환한다(){
//        // given
//        Long[] storeIds = {1L, 2L, 3L, 4L};
//        String[] storeNames = {"가게1", "가게2", "가게3", "가게4"};
//        String[] storeLocations = {"위치1", "위치2", "위치3", "위치4"};
//        String[] imgUrls = {"이미지1", "이미지2", "이미지3", "이미지4"};
//        boolean[] storeLikes = {false, false, false, false, false};
//
//        MyPageResDto Dto = makeTestGetMyPageResDto(1L, 1L, "행복", 1L,
//                "달콤한", storeIds, storeNames, storeLocations, imgUrls, storeLikes);
//
//        given(myPageService.getMyPage(any(UserPrincipal.class)).willReturn(Dto);
//
//        //when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.get("/dobong/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//
//        //then
//        resultActions.andExpect(status().isOk())
//                .andExpect(expectedGetMyPageResDto());
//
//    }

}