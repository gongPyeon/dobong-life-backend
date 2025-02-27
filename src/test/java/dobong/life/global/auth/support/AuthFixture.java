package dobong.life.global.auth.support;

import dobong.life.domain.user.User;
import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.dto.RegisterUserCommand;
import dobong.life.global.auth.dto.TokenCommand;
import dobong.life.global.auth.enums.Role;
import dobong.life.global.auth.enums.SocialType;
import dobong.life.global.auth.jwt.JwtProvider;
import dobong.life.global.auth.service.AuthenticationService;
import dobong.life.global.auth.service.CustomUserDetailService;
import dobong.life.global.auth.service.UserService;
import dobong.life.global.auth.service.principal.UserPrincipal;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthFixture {

    private static final Long USERID = 1L;
    private static final String NICKNAME = "홍길동";
    private static final String EMAIL = "honggil@konkuk.ac.kr";
    private static final String PWD = "Oauth2****";
    private static final String PROVIDER_ID = "PROVIDER_ID";
    private static final Role ROLE = Role.USER_OAUTH2;
    public static final String KEY = "WkdGN2tMZE9qM3RyTDRqYVhPZG5uV2t5QlJGV3VwREl1TFdGbFJXVlR3WkhwdldhQ1JX";
    private static final String PROVIDER = "kakao";
    private static final String REDIRECT_URI = "http://localhost:8080/login/oauth2/code/kakao";
    private static final String ACCESS_TOKEN = "access-token";
    private static final String AUTORIZATION_URI = "http://localhost:8080/login/oauth2/auth";
    private static final String TOKEN_URI = "http://localhost:8080/login/oauth2/token";
    private static final String USERINFO_URI = "http://localhost:8080/login/oauth2/info";
    private static final Long EXPIRED_TIME = 3600L;
    public static JwtProvider jwtProvider(){
        return new JwtProvider(KEY);
    }

    public static AuthenticationService authenticationService(){
        AuthenticationService mockAuthenticationService = mock(AuthenticationService.class);
        when(mockAuthenticationService.getAuthentication(any())).thenReturn(authentication());

        return mockAuthenticationService;
    }
    public static Authentication authentication(){
        RegisterResponse registerResponse = new RegisterResponse(USERID, NICKNAME, EMAIL, PWD, PROVIDER_ID, ROLE);
        UserPrincipal userPrincipal = new UserPrincipal(registerResponse);
        return new UsernamePasswordAuthenticationToken(
                userPrincipal,
                null,
                userPrincipal.getAuthorities()
        );
    }

    public static String accessToken(){
        JwtProvider jwtProvider = jwtProvider();
        TokenCommand tokenCommand =  jwtProvider.generateToken(authentication());
        return tokenCommand.getAccessToken();
    }

    public static UserSignUpDto userSignUpDto(){
        return UserSignUpDto.builder()
                .id(EMAIL)
                .pwd(PWD)
                .pwdCheck(null)
                .nickName(NICKNAME)
                .build();
    }

    public static UserSignUpDto userSignUpDtoById(String id){
        return UserSignUpDto.builder()
                .id(id)
                .pwd(PWD)
                .pwdCheck(PWD)
                .nickName(NICKNAME)
                .build();
    }

    public static UserSignUpDto userSignUpDtoByPwd(String pwd){
        return UserSignUpDto.builder()
                .id(EMAIL)
                .pwd(pwd)
                .pwdCheck(PWD)
                .nickName(NICKNAME)
                .build();
    }

    public static UserSignUpDto userSignUpDtoByName(String name){
        return UserSignUpDto.builder()
                .id(EMAIL)
                .pwd(PWD)
                .pwdCheck(PWD)
                .nickName(name)
                .build();
    }

    private static ClientRegistration clientRegistration(){
        return ClientRegistration.withRegistrationId(PROVIDER)
                .clientId(PROVIDER_ID)
                .redirectUri(REDIRECT_URI)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationUri(AUTORIZATION_URI)
                .tokenUri(TOKEN_URI)
                .userInfoUri(USERINFO_URI)
                .userNameAttributeName(NICKNAME)
                .build();
    }

    private static OAuth2AccessToken oAuth2AccessToken(){
        return new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                ACCESS_TOKEN,
                Instant.now(),
                Instant.now().plusSeconds(EXPIRED_TIME)
        );
    }

    public static OAuth2UserRequest oAuth2UserRequest(){
        return new OAuth2UserRequest(clientRegistration(), oAuth2AccessToken());
    }

    public static OAuth2User oAuth2User(){
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", USERID);
        attributes.put("name", NICKNAME);
        attributes.put("email", EMAIL);

        OAuth2User oAuth2User = new DefaultOAuth2User(
                Collections.emptyList(),
                attributes,
                "name"
        );

        return oAuth2User;
    }

    public static RegisterUserCommand registerUserCommand() {
        return RegisterUserCommand.builder()
                .name(NICKNAME)
                .email(EMAIL)
                .providerId(PROVIDER_ID)
                .providerType(SocialType.KAKAO)
                .role(ROLE)
                .build();
    }

    public static User user() {
        return User.builder()
                .nickName(NICKNAME)
                .email(EMAIL)
                .providerType(SocialType.KAKAO)
                .providerId(PROVIDER_ID)
                .role(ROLE)
                .build();
    }
}
