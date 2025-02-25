package dobong.life.global.auth.controller;

import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.service.AuthService;
import dobong.life.global.util.constant.DEFINE;
import dobong.life.global.util.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@ResponseBody
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public BaseResponse<String> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto){
        String message = authService.signUp(userSignUpDto);
        return new BaseResponse<>(message);
    }
}
