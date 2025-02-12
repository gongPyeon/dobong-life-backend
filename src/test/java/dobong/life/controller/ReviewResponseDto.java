package dobong.life.controller;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class ReviewResponseDto {
    public static ResultMatcher expectedPostReviewResDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result").value("리뷰 등록이 완료되었습니다!").match(result);
        };
    }
}
