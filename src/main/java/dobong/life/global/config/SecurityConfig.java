package dobong.life.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dobong.life.global.auth.handler.CustomLogoutSuccessHandler;
import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.jwt.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import dobong.life.global.auth.handler.AuthenticationSuccessHandler;
import dobong.life.global.auth.jwt.filter.ExceptionHandlerFilter;
import dobong.life.global.auth.jwt.filter.JwtAuthenticationFilter;
import dobong.life.global.auth.service.AuthenticationService;
import dobong.life.global.auth.service.CustomOAuth2UserService;
import dobong.life.global.auth.service.CustomUserDetailService;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomUserDetailService customUserDetailService;
    private final AuthenticationService authenticationService;
    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
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
                        .requestMatchers("/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/test/**",
                                "/auth/**",
                                "/login").permitAll()
                        .anyRequest().authenticated());
        // oauth2 로그인
        http
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(info -> info.userService(customOAuth2UserService)) // OAuth2 로그인 과정에서 사용자 정보를 가져오는 역할
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler));
        // 로그아웃 (비어있을때도 로그아웃 성공이 뜬다)
        http
                .logout(logout -> logout
                        .logoutSuccessHandler(customLogoutSuccessHandler));
        // 순서 : LogoutFilter -> ExceptionHandler -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter
        http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtProvider, authenticationService), CustomJsonUsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class);

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
        provider.setUserDetailsService(customUserDetailService);

        /**
         * Spring Security에서는 사용자를 찾지 못했을 때 보안상의 이유로 UsernameNotFoundException을 캐치한 다음
         * BadCredentialsException으로 변환하여 던지는 경우가 있다.
         * 이는 공격자가 유효한 사용자명과 유효하지 않은 사용자명을 구분하지 못하게 하기 위함이다.
         */
        provider.setHideUserNotFoundExceptions(false);
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

}
