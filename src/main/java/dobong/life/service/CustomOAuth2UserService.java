package dobong.life.service;

import dobong.life.dto.RegisterResponse;
import dobong.life.dto.RegisterUserCommand;
import dobong.life.entity.User;
import dobong.life.enums.Role;
import dobong.life.enums.SocialType;
import dobong.life.repository.UserRepository;
import dobong.life.service.principal.CustomUser;
import dobong.life.userInfo.OAuth2UserInfo;
import dobong.life.userInfo.OAuth2UserInfoFactory;
import dobong.life.util.exception. InvalidProviderException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;
    // OAuth2 로그인 시 사용자 정보를 가져와서 가공하는 서비스

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();
        /**
         * OAuth2 로그인 시 제공자로부터 받은 사용자 정보
         * {
         *     "sub": "123456789012345678901",
         *     "name": "홍길동",
         *     "given_name": "길동",
         *     "family_name": "홍",
         *     "picture": "https://lh3.googleusercontent.com/a-/AOh14Gj...",
         *     "email": "hong@example.com",
         *     "email_verified": true,
         *     "locale": "ko"
         * }
         */
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId(); // GOOGLE, NAVER
        SocialType providerType = SocialType.valueOf(registrationId.toUpperCase()); // 대문자로 SocialType 가져오기
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, attributes);


        // 사용자를 등록하거나 조회한다
        RegisterUserCommand registerUserCommand = RegisterUserCommand.of(
                oAuth2UserInfo.getName(),
                oAuth2UserInfo.getEmail(),
                oAuth2UserInfo.getOAuth2Id(),
                providerType,
                Role.ROLE_USER
        );

        RegisterResponse registerResponse = userService.getOrRegisterUser(registerUserCommand);
        return new CustomUser(registerResponse, attributes);
    }
}