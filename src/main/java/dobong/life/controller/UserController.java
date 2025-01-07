package dobong.life.controller;

import dobong.life.dto.UserSignUpDto;
import dobong.life.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private  final UserService userService;

    @PostMapping("/sign-up")
    @ResponseBody
    public String signUp(@RequestBody UserSignUpDto userSignUpDto){
        userService.signUp(userSignUpDto);
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    @ResponseBody
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
