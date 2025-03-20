package dobong.life.base.store.controller.expected.dto;

import dobong.life.base.store.support.StoreFixture;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class StoreResDTO {
    public static ResultMatcher expectedGetStoresResDTO() {
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.storesDTOList[0].categoryName").value(StoreFixture.CATEGORY_NAME).match(result);
            MockMvcResultMatchers.jsonPath("$.result.storesDTOList[0].items[0].name").value(StoreFixture.STORE_NAME).match(result);
            MockMvcResultMatchers.jsonPath("$.result.storesDTOList[0].items[0].address").value(StoreFixture.ADDRESS).match(result);
            MockMvcResultMatchers.jsonPath("$.result.storesDTOList[0].items[0].categories", containsInAnyOrder(StoreFixture.CATEGORIES));
            MockMvcResultMatchers.jsonPath("$.result.storesDTOList[0].items[0].like").value(false).match(result);
        };
    }

    public static ResultMatcher expectedGetStoresByIdResDTO() {
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.categoryName").value(StoreFixture.CATEGORY_NAME).match(result);
            MockMvcResultMatchers.jsonPath("$.result.hashTagDTOList[0].hashTagName").value(StoreFixture.HASH_TAG).match(result);
            MockMvcResultMatchers.jsonPath("$.result.hashTagDTOList[0].items[0].name").value(StoreFixture.STORE_NAME).match(result);
            MockMvcResultMatchers.jsonPath("$.result.hashTagDTOList[0].items[0].address").value(StoreFixture.ADDRESS).match(result);
            MockMvcResultMatchers.jsonPath("$.result.hashTagDTOList[0].items[0].categories", containsInAnyOrder(StoreFixture.CATEGORIES));
            MockMvcResultMatchers.jsonPath("$.result.hashTagDTOList[0].items[0].like").value(false).match(result);
        };
    }

    public static ResultMatcher expectedGetStoresByQueryResDTO() {
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.filter", containsInAnyOrder(StoreFixture.FILTER));
            MockMvcResultMatchers.jsonPath("$.result.query").value(StoreFixture.QUERY).match(result);
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].name").value(StoreFixture.STORE_NAME).match(result);
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].address").value(StoreFixture.ADDRESS).match(result);
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].categories", containsInAnyOrder(StoreFixture.CATEGORIES));
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].like").value(false).match(result);
        };
    }


    public static ResultMatcher expectedGetStoreLikeResDTO() {
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].name").value(StoreFixture.STORE_NAME).match(result);
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].address").value(StoreFixture.ADDRESS).match(result);
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].categories", containsInAnyOrder(StoreFixture.CATEGORIES));
            MockMvcResultMatchers.jsonPath("$.result.itemDTOList[0].like").value(false).match(result);
        };
    }
}
