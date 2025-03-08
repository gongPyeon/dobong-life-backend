package dobong.life.global.util.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.global.util.response.status.StatusCode;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseUtil {
    public static void setResponse(StatusCode status, HttpServletResponse response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        BaseErrorResponse baseErrorResponse = BaseErrorResponse.of(status).getBody();
        response.setStatus(status.getCode());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(baseErrorResponse)
        );
    }
}
