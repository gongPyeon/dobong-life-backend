package dobong.life.controller;

import dobong.life.dto.UserSignUpDto;
import dobong.life.service.UserService;
import dobong.life.util.DEFINE;
import dobong.life.util.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@ResponseBody
public class UserController {

    private  final UserService userService;

    @PostMapping("/sign-up")
    public BaseResponse<String> signUp(@RequestBody UserSignUpDto userSignUpDto){
        userService.signUp(userSignUpDto);
        return new BaseResponse<>(DEFINE.SIGN_UP_OK);
    }
}
