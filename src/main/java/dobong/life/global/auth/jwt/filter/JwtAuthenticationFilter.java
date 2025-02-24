package dobong.life.global.auth.jwt.filter;

import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private static final String HEADER = "Authorization";
    private final JwtProvider jwtProvider;
    private final AuthenticationService authenticationService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String accessToken = request.getHeader(HEADER);
        log.info("doFilter의 accessToekn = {}", accessToken);

        if(Objects.nonNull(accessToken)) {
            Claims claims = jwtProvider.validateToken(jwtProvider.extractBearer(accessToken));
            Authentication authentication = authenticationService.getAuthentication(claims);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(req, res);
    }
}
