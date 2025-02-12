package dobong.life.controller.expexted.dto;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class LikeResponseDto {
    public static ResultMatcher expectedPostLikeDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result").value("좋아요 적용이 완료되었습니다!").match(result);
        };
    }
}

