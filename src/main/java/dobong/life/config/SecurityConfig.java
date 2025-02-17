package dobong.life.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import dobong.life.handler.AuthenticationFailureHandler;
import dobong.life.handler.AuthenticationSuccessHandler;
import dobong.life.jwt.JwtAuthenticationFilter;
import dobong.life.jwt.JwtService;
import dobong.life.repository.CookieAuthorizationRequestRepository;
import dobong.life.repository.RefreshTokenRepository;
import dobong.life.service.CustomOAuth2UserService;
import dobong.life.service.LoginService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final LoginService loginService;
    private final ObjectMapper objectMapper;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // sessionless이므로 httpBasic, csrf, formLogin, session disable
        http
                .cors(cors -> cors.configurationSource(corsConfig.configurationSource()))
                .csrf(csrf ->csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .rememberMe(rememberMe -> rememberMe.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 요청에 대한 권한 설정
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/oauth2/**", "/", "/login", "/login-test", "/sign-up", "/ec2").permitAll()
                        .anyRequest().authenticated());

        // oauth2 로그인
        http
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(info -> info.userService(customOAuth2UserService)) // OAuth2 로그인 과정에서 사용자 정보를 가져오는 역할
                        /**
                         * userInfoEndpoint를 통해 해당 액세스 토큰을 사용하여 사용자 정보를 요청
                         * Provider가 사용자 정보를 반환
                         * 애플리케이션이 해당 정보를 사용하여 회원 가입 또는 로그인 처리
                         */
                        .successHandler(authenticationSuccessHandler) // 성공 핸들러
                        .failureHandler(authenticationFailureHandler)); // 실패 핸들러

        // 로그아웃
        http
                .logout(logout -> logout
                        .clearAuthentication(true)
                        .deleteCookies("accessToken")
                        .logoutSuccessUrl("/logout-test"));

        // 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter
        http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
        http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt 암호화 사용
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    @Bean
    public CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter() {
        CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordLoginFilter
                = new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
        customJsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return customJsonUsernamePasswordLoginFilter;
    }

    // jwt filter 설정
    @Bean
    public Filter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService, refreshTokenRepository);
        return jwtAuthenticationFilter;
    }

}
