package dobong.life.handler;

import dobong.life.dto.Token;
import dobong.life.jwt.JwtService;
import dobong.life.lib.CookieUtils;
import dobong.life.repository.CookieAuthorizationRequestRepository;
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

@Slf4j
@Component
@RequiredArgsConstructor

public class AuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final String ACCESS_TOKEN = "accessToken";
    @Value("${jwt.access.expiration}")
    private int ACCESS_TOKEN_MAXAGE;

    private final JwtService jwtService;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;

    @PostConstruct
    public void init() {
        setDefaultTargetUrl("/login-test");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 모든 로그인에 JWT 생성
        Token token = jwtService.generateToken(authentication);
        CookieUtils.addCookie(response, ACCESS_TOKEN, token.getAccessToken(), ACCESS_TOKEN_MAXAGE);
        clearAuthenticationAttributes(request, response);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        // 남아 있을 필요 없는 인증 정보를 깔끔하게 정리하는 역할
        super.clearAuthenticationAttributes(request);
        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

}
