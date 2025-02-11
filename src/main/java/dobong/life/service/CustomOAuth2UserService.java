package dobong.life.service;

import dobong.life.entity.User;
import dobong.life.enums.Role;
import dobong.life.enums.SocialType;
import dobong.life.repository.UserRepository;
import dobong.life.service.principal.UserPrincipal;
import dobong.life.userInfo.OAuth2UserInfo;
import dobong.life.userInfo.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        log.info("loadUser 진입");
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);

        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        //OAuth2 로그인 플랫폼 구분
        SocialType socialType = SocialType.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(socialType, oAuth2User.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail()).orElse(null);
        //이미 가입된 경우
//        if (user != null) {
//            if (!user.getSocialType().equals(socialType)) {
//                throw new RuntimeException("Email already signed up.");
//            }
//            user = updateUser(user, oAuth2UserInfo);
//        }
//        //가입되지 않은 경우
//        else {
//            user = registerUser(socialType, oAuth2UserInfo);
//        }
        user = registerUser(socialType, oAuth2UserInfo);
        return UserPrincipal.create(user, oAuth2UserInfo.getAttributes());
    }

    private User registerUser(SocialType socialType, OAuth2UserInfo oAuth2UserInfo) {
        User user = User.builder()
                .email(oAuth2UserInfo.getEmail())
                .password("Oauth2!")
                .name(oAuth2UserInfo.getName())
                .oauth2Id(oAuth2UserInfo.getOAuth2Id())
                .socialType(socialType)
                .role(Role.ROLE_USER)
                .build();

        return userRepository.save(user);
    }

    private User updateUser(User user, OAuth2UserInfo oAuth2UserInfo) {

        return userRepository.save(user.update(oAuth2UserInfo));
    }
}
