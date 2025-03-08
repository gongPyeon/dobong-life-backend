package dobong.life.global.auth.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.global.auth.exception.AuthFailureException;
import dobong.life.global.auth.exception.InvalidJwtException;
import dobong.life.global.auth.exception.InvalidProviderException;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.BaseErrorCode;
import dobong.life.global.util.response.status.StatusCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    // do filter에 대한 보안적인 예외
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (InvalidJwtException ex){
            setErrorResponse(BaseErrorCode.INVALID_TOKEN, response);
        } catch (InvalidProviderException ex){
            setErrorResponse(BaseErrorCode.INVALID_OAUTH2, response);
        }catch (BaseException ex){
            setErrorResponse(BaseErrorCode.FAIL_LOGIN, response);
        }
    }

    public void setErrorResponse(StatusCode status, HttpServletResponse response) throws IOException {

        BaseErrorResponse baseErrorResponse = BaseErrorResponse.of(status).getBody();
        response.setStatus(status.getCode());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(baseErrorResponse)
        );
    }
}

