package dobong.life.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TestController {
    @GetMapping("/login-test")
    @ResponseBody
    public String loginInfo() {
        return "login";
    }

    @GetMapping("/logout-test")
    @ResponseBody
    public String logoutInfo() {
        return "logout"; // login page로 변경
    }

    @GetMapping("/hi")
    @ResponseBody
    public String hiInfo() {
        return "hi"; // accessToken 재발급 확인 테스트용
    }

    @GetMapping("/ec2")
    @ResponseBody
    public String ec2Info() {
        return "ec2-cicd!"; //ec2 테스트용
    }

    @GetMapping("/ssh")
    @ResponseBody
    public String sshInfo() {
        return "ssh"; //ec2 테스트용
    }

    @GetMapping("/jwt-test")
    @ResponseBody
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
