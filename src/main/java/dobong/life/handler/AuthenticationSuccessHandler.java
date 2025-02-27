package dobong.life.handler;

import dobong.life.dto.TokenCommand;
import dobong.life.jwt.JwtService;
import dobong.life.lib.CookieUtils;
import dobong.life.repository.CookieAuthorizationRequestRepository;
import dobong.life.service.RedisUtil;
import dobong.life.service.principal.UserPrincipal;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor

public class AuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final String ACCESS_TOKEN = "accessToken";
    @Value("${jwt.access.expiration}")
    private int ACCESS_TOKEN_MAXAGE;

    private final JwtService jwtService;
    private final RedisUtil redisUtil;
    @PostConstruct
    public void init() {
        setDefaultTargetUrl("/login-test");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 모든 로그인에 JWT 생성
        TokenCommand token = jwtService.generateToken(authentication);
        CookieUtils.addCookie(response, ACCESS_TOKEN, token.getAccessToken(), ACCESS_TOKEN_MAXAGE);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        redisUtil.saveRefreshToken(userPrincipal.getId(), token.getRefreshToken());
    }

//    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
//        // 남아 있을 필요 없는 Oauth2 인증 정보를 깔끔하게 정리하는 역할
//        super.clearAuthenticationAttributes(request);
//        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
//    }

}
