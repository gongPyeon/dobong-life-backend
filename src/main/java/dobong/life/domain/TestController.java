package dobong.life.domain;

import dobong.life.global.util.response.status.BaseCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.http.HttpResponse;

import static dobong.life.global.util.response.ResponseUtil.setResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test/")
public class TestController {
    @GetMapping("/login")
    @ResponseBody
    public String loginInfo() {
        return "login";
    }

    @GetMapping("/logout")
    public void logoutInfo(HttpServletResponse response) throws IOException {
        setResponse(BaseCode.SUCCESS_LOGOUT, response);
    }

    @GetMapping("/hi")
    @ResponseBody
    public String hiInfo() {
        return "hi"; // accessToken 재발급 확인 테스트용
    }

    @GetMapping("/ec2")
    @ResponseBody
    public String ec2Info() {
        return "ec2-cicd/20250211"; //ec2 테스트용
    }

    @GetMapping("/ssh")
    @ResponseBody
    public String sshInfo() {
        return "ssh"; //ec2 테스트용
    }

    @GetMapping("/jwt")
    @ResponseBody
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
