package dobong.life.global.auth.handler;

import dobong.life.global.auth.dto.TokenCommand;
import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.util.cookie.CookieUtils;
import dobong.life.global.util.redis.RedisUtil;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.util.response.status.BaseCode;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

import static dobong.life.global.util.response.ResponseUtil.setResponse;

@Slf4j
@Component
@RequiredArgsConstructor

public class AuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final String ACCESS_TOKEN = "accessToken";
    @Value("${jwt.access.expiration}")
    private int ACCESS_TOKEN_MAXAGE;

    private final JwtProvider jwtProvider;
    private final RedisUtil redisUtil;
    @PostConstruct
    public void init() {
        setDefaultTargetUrl("/test/login");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 모든 로그인에 JWT 생성
        TokenCommand token = jwtProvider.generateToken(authentication);
//        CookieUtils.addCookie(response, ACCESS_TOKEN, token.getAccessToken(), ACCESS_TOKEN_MAXAGE);

        // TEST를 위한 헤더설정
        response.setHeader("Authorization", "Bearer " + token.getAccessToken());

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        redisUtil.saveRefreshToken(userPrincipal.getEmail(), token.getRefreshToken());
        setResponse(BaseCode.SUCCESS_LOGIN, response);
    }

//    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
//        // 남아 있을 필요 없는 Oauth2 인증 정보를 깔끔하게 정리하는 역할
//        super.clearAuthenticationAttributes(request);
//        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
//    }

}
