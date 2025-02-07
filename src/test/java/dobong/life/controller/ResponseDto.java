package dobong.life.controller;

import dobong.life.dto.StoreItemResDto;
import dobong.life.dto.StoresResDto;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static dobong.life.controller.TestResponse.makeTestGetStoreItemResDto;
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

    public static ResultMatcher expectedGetStoresResDtoByQuery() { // 첫번째 요소
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.categoryId").value(1L).match(result);

            MockMvcResultMatchers.jsonPath("$.result.items[0].tagId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].parentTagName").value("행복").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].subTagId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].subTagName").value("달콤한").match(result);

            MockMvcResultMatchers.jsonPath("$.result.items[0].stores.length()").value(2).match(result); // 가게가 2개인지 확인

            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeName").value("순대1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeLocation").value("위치1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].imgUrl").value("이미지1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[0].storeLike").value(false).match(result);

            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[1].storeId").value(2L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[1].storeName").value("순대2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[1].storeLocation").value("위치2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[1].imgUrl").value("이미지2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].stores[1].storeLike").value(true).match(result);
        };
    }

    public static ResultMatcher expectedGetStoresFilterResDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.categoryId").value(1L).match(result);

            MockMvcResultMatchers.jsonPath("$.result.categoryNames[0]").value("분식").match(result);
            MockMvcResultMatchers.jsonPath("$.result.subTagNames[0]").value("맛집").match(result);

            MockMvcResultMatchers.jsonPath("$.result.items[0].storeId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].storeName").value("순대1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].storeLocation").value("위치1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].imgUrl").value("이미지1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[0].storeLike").value(false).match(result);

            MockMvcResultMatchers.jsonPath("$.result.items[1].storeId").value(2L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[1].storeName").value("순대2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[1].storeLocation").value("위치2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[1].imgUrl").value("이미지2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.items[1].storeLike").value(true).match(result);

        };
    }

    public static ResultMatcher expectedGetStoreItemResDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.categoryId").value(1L).match(result);

            MockMvcResultMatchers.jsonPath("$.result.storeBasicInfo.storeId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeBasicInfo.storeName").value("가게1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeBasicInfo.storeLocation").value("위치1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeBasicInfo.imgUrl").value("이미지1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeBasicInfo.storeLike").value(false).match(result);

            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.subCategoryName").value("서브카테고리명1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.storeLocationDetail").value("상세주소1").match(result);

            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.storeMenu[0]").value("메뉴1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.storeMenu[1]").value("메뉴2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.storeMenu[2]").value("메뉴3").match(result);

            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.storeKeyword[0]").value("키워드1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.storeKeyword[1]").value("키워드2").match(result);
            MockMvcResultMatchers.jsonPath("$.result.storeDetailInfo.storeKeyword[2]").value("키워드3").match(result);

        };
    }
}
