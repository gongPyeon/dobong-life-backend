package dobong.life.global.auth.controller.expected.dto;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AuthResponseDto {
    public static ResultMatcher expectedPostAuthDto(){
        return result -> {
            MockMvcResultMatchers.jsonPath("$.result").value("회원가입에 성공했습니다.").match(result);
        };
    }
}
