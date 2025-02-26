package dobong.life.global.auth.jwt.filter;

import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private JwtProvider jwtProvider;
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp(){
        jwtProvider = Mockito.mock(JwtProvider.class);
        authenticationService = Mockito.mock(AuthenticationService.class);
        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtProvider, authenticationService);
    }
    @Nested
    @DisplayName("doFilter 메서드 실행 시")
    class DoFilterTest{
        @Test
        @DisplayName("유효한 토큰이 없는 경우 다음 필터로 진행")
        void noTokenProceedsToNextFilter() throws ServletException, IOException{
            // given
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            FilterChain filterChain = mock(FilterChain.class);

            // when
            when(jwtProvider.validateToken(anyString())).thenReturn(null);
            jwtAuthenticationFilter.doFilter(request, response, filterChain);

            // then
            verify(filterChain).doFilter(request, response);
            verify(authenticationService, never()).getAuthentication(any());
        }

        @Test
        @DisplayName("유효한 토큰이 있는 경우 인증 처리 후 다음 필터로 진행")
        void validTokenAuthenticated() throws ServletException, IOException{
            // given
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            FilterChain filterChain = mock(FilterChain.class);

            String token = "valid.jwt.token";
            Claims claims = mock(Claims.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());

            // when
            when(jwtProvider.validateToken(token)).thenReturn(claims);
            when(authenticationService.getAuthentication(claims)).thenReturn(authentication);
            request.addHeader("Authorization", "Bearer " + token);
            jwtAuthenticationFilter.doFilter(request, response, filterChain);

            // then
            verify(filterChain).doFilter(request, response);
            verify(authenticationService).getAuthentication(claims); // 왜 이게 호출이 안되는거지?
        }

        @Test
        @DisplayName("유효하지 않은 토큰인 경우 인증 처리 없이 다음 필터로 진행")
        void invalidTokenProceedsToNextFilter() throws ServletException, IOException {
            // given
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();
            FilterChain filterChain = Mockito.mock(FilterChain.class);

            String token = "invalid.jwt.token";

            // 유효하지 않은 토큰 설정
            when(jwtProvider.validateToken(token)).thenReturn(null);
            jwtAuthenticationFilter.doFilter(request, response, filterChain);

            // then
            verify(filterChain).doFilter(request, response);
            verify(authenticationService, never()).getAuthentication(any());
        }
    }

}