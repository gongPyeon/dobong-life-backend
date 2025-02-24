package dobong.life.global.auth.service;

import dobong.life.global.auth.dto.RegisterResponse;
import dobong.life.global.auth.dto.RegisterUserCommand;
import dobong.life.global.auth.enums.Role;
import dobong.life.global.auth.enums.SocialType;
import dobong.life.global.auth.service.principal.UserPrincipal;
import dobong.life.global.auth.service.userInfo.OAuth2UserInfo;
import dobong.life.global.auth.service.userInfo.OAuth2UserInfoFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
        SocialType providerType = SocialType.valueOf(registrationId.toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, attributes);


        RegisterUserCommand registerUserCommand = RegisterUserCommand.of(
                oAuth2UserInfo.getName(),
                oAuth2UserInfo.getEmail(),
                oAuth2UserInfo.getOAuth2Id(),
                providerType,
                Role.USER_OAUTH2
        );

        RegisterResponse registerResponse = userService.getOrRegisterUser(registerUserCommand);
        return new UserPrincipal(registerResponse, attributes);
    }
}