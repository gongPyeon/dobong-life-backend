package dobong.life.global.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.ErrorResponse;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;
import dobong.life.global.util.response.status.StatusCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {

        try {
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
