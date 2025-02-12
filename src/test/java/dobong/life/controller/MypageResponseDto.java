package dobong.life.controller;

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
}
