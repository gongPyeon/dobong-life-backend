package dobong.life.global.auth.handler;

import dobong.life.global.util.response.status.BaseErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static dobong.life.global.util.response.ResponseUtil.setResponse;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            response.sendRedirect("/test/logout"); // logout 후 접근하는 home
        } else {
            setResponse(BaseErrorCode.INVALID_LOGOUT, response);
        }
    }
}

