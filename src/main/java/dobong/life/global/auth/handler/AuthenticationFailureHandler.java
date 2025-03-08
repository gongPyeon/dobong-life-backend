package dobong.life.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.status.BaseErrorCode;
import dobong.life.global.util.response.status.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    // 로그인에 대한 예외
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {

        try {
            log.error(authenticationException.getMessage());
            if (authenticationException instanceof UsernameNotFoundException) {
                setErrorResponse(BaseErrorCode.USER_NOT_FOUND, response);
            } else if (authenticationException instanceof BadCredentialsException) {
                setErrorResponse(BaseErrorCode.INVALID_PASSWORD, response);
            } else {
                setErrorResponse(BaseErrorCode.FAIL_LOGIN, response);
            }
        } catch (Exception e) {
            log.error("Authentication failure handling error", e);
            setErrorResponse(BaseErrorCode.FAIL_LOGIN, response);
        }
    }

    private void setErrorResponse(StatusCode status, HttpServletResponse response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        BaseErrorResponse baseErrorResponse = BaseErrorResponse.of(status).getBody();
        response.setStatus(status.getCode());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(baseErrorResponse)
        );
    }
}
