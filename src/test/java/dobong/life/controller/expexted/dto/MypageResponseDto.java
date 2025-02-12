package dobong.life.controller.expexted.dto;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MypageResponseDto {
    public static ResultMatcher expectedGetMyPageResDto() { // 첫번째 요소
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.email").value("test@naver.com").match(result);
            MockMvcResultMatchers.jsonPath("$.result.name").value("test").match(result);
            MockMvcResultMatchers.jsonPath("$.result.myReviewCount").value(0).match(result);
            MockMvcResultMatchers.jsonPath("$.result.myReviewLikeCount").value(0).match(result);
            MockMvcResultMatchers.jsonPath("$.result.myFoodLike").value(0).match(result);
            MockMvcResultMatchers.jsonPath("$.result.myPlaceLike").value(0).match(result);
            MockMvcResultMatchers.jsonPath("$.result.myBusinessLike").value(0).match(result);
        };
    }

    public static ResultMatcher expectedGetMyPageReviewResDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result.reviews[0].storeId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result.reviews[0].name").value("test").match(result);
            MockMvcResultMatchers.jsonPath("$.result.reviews[0].reviewContent").value("good").match(result);
            MockMvcResultMatchers.jsonPath("$.result.reviews[0].selectedKeywords[0]").value("test1").match(result);
            MockMvcResultMatchers.jsonPath("$.result.reviews[0].selectedKeywords[1]").value("test2").match(result);
        };
    }

    public static ResultMatcher expectedGetStoreBasicInfoListDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result[0].storeId").value(1L).match(result);
            MockMvcResultMatchers.jsonPath("$.result[0].storeName").value("test").match(result);
            MockMvcResultMatchers.jsonPath("$.result[0].storeLocation").value("location").match(result);
            MockMvcResultMatchers.jsonPath("$.result[0].imgUrl").value("img").match(result);
            MockMvcResultMatchers.jsonPath("$.result[0].storeLike").value(true).match(result);
        };
    }
}
