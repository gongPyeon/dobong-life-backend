package dobong.life.global.auth.support;

import dobong.life.global.auth.controller.request.UserSignUpDto;
import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.dto.TokenCommand;
import dobong.life.global.auth.enums.Role;
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
import org.springframework.test.context.bean.override.mockito.MockitoBean;

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

    public static UserSignUpDto userSignUpDtoById(String Id){
        return UserSignUpDto.builder()
                .Id(Id)
                .pwd(PWD)
                .pwdCheck(PWD)
                .nickName(NICKNAME)
                .role(Role.USER_REGULAR)
                .build();
    }

    public static UserSignUpDto userSignUpDtoByPwd(String pwd){
        return UserSignUpDto.builder()
                .Id(EMAIL)
                .pwd(pwd)
                .pwdCheck(PWD)
                .nickName(NICKNAME)
                .role(Role.USER_REGULAR)
                .build();
    }

    public static UserSignUpDto userSignUpDtoByName(String name){
        return UserSignUpDto.builder()
                .Id(EMAIL)
                .pwd(PWD)
                .pwdCheck(PWD)
                .nickName(name)
                .role(Role.USER_REGULAR)
                .build();
    }
}
