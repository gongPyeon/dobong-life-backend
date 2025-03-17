package dobong.life.base;

import dobong.life.base.user.repository.UserRepository;
import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.jwt.filter.JwtAuthenticationFilter;
import dobong.life.global.auth.service.AuthenticationService;
import dobong.life.global.auth.service.CustomUserDetailService;
import dobong.life.global.auth.service.UserService;
import dobong.life.global.auth.support.AuthFixture;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@TestConfiguration
@EnableWebSecurity
public class TestSecurityConfig {

    @Bean
    public JwtProvider jwtProvider() {
        return AuthFixture.jwtProvider();
    }

    @Bean
    public AuthenticationService authenticationService() {
        return AuthFixture.authenticationService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 원본 프로덕션 코드와 유사한 방식으로 설정
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/sign-up").permitAll()
                        .anyRequest().authenticated());

        // 중요: 필터 추가 방식을 프로덕션 코드와 동일하게 설정
        http.addFilterBefore(
                new JwtAuthenticationFilter(jwtProvider(), authenticationService()),
                UsernamePasswordAuthenticationFilter.class
        );

        http.authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) {
                return AuthFixture.authentication();
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };
    }
}


