package dobong.life.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.base.review.service.ReviewService;
import dobong.life.base.store.service.StoreAndReviewService;
import dobong.life.base.store.service.StoreService;
import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.jwt.filter.JwtAuthenticationFilter;
import dobong.life.global.auth.service.AuthenticationService;
import dobong.life.global.auth.service.CustomUserDetailService;
import dobong.life.global.auth.service.UserService;
import dobong.life.global.auth.support.AuthFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest
@Import(TestSecurityConfig.class)
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockitoBean
    protected StoreService storeService;

    @MockitoBean
    protected StoreAndReviewService storeAndReviewService;

    @MockitoBean
    protected ReviewService reviewService;

    @Autowired
    protected ObjectMapper objectMapper;
    protected String accessToken;

    protected static final String BEARER = "Bearer ";
    protected static final String AUTHORIZATION = "Authorization";

    @BeforeEach
    void authenticationSetUp() {
        accessToken = BEARER + AuthFixture.accessToken();
    }

}
