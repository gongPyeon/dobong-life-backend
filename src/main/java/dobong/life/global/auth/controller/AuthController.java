package dobong.life.global.auth.controller;

import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.service.AuthService;
import dobong.life.global.util.constant.DEFINE;
import dobong.life.global.util.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@ResponseBody
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public BaseResponse<String> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto){
        String message = authService.signUp(userSignUpDto);
        return new BaseResponse<>(message);
    }

    @PostMapping("/id")
    public BaseResponse<String> checkDupId(@RequestBody Map<String, String> request){
        String message = authService.checkDupId(request.get("id"));
        return new BaseResponse<>(message);
    }

    @PostMapping("/nickname")
    public BaseResponse<String> checkDupName(@RequestBody Map<String, String> request){
        String message = authService.checkDupNickName(request.get("nickname"));
        return new BaseResponse<>(message);
    }
}
