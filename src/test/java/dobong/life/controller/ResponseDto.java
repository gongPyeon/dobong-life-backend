package dobong.life.controller;

import dobong.life.dto.StoresResDto;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static dobong.life.controller.TestResponse.makeTestGetStoresResDto;

public class ResponseDto {
    public static ResultMatcher expectedGetStoresResDto() { // 첫번째 요소
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.categoryId").value(1L).match(result);

            MockMvcResultMatchers.jsonPath("$.result.items[0].tagId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].parentTagName").value("행복").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].subTagId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].subTagName").value("달콤한").match(result);

            MockMvcResultMatchers.jsonPath("$.result.items[0].stores.length()").value(4).match(result); // 가게가 4개인지 확인

            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeName").value("가게1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeLocation").value("위치1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].imgUrl").value("이미지1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeLike").value(false).match(result);
        };
    }
}
