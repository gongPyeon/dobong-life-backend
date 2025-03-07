package dobong.life.global.auth.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.global.auth.exception.AuthFailureException;
import dobong.life.global.util.response.BaseErrorResponse;
import dobong.life.global.util.response.BaseException;
import dobong.life.global.util.response.status.StatusCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (BaseException ex) {
            setErrorResponse(ex.getStatus(), response);
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

