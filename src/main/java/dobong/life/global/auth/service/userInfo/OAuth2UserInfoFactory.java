package dobong.life.global.auth.service.userInfo;

import dobong.life.global.auth.enums.SocialType;
import dobong.life.global.auth.exception.InvalidProviderException;
import dobong.life.global.util.response.status.BaseErrorCode;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(SocialType socialType, Map<String, Object> attributes){
        switch(socialType){
            case GOOGLE : return new GoogleOAuth2User(attributes);
            case NAVER : return new NaverOAuth2User(attributes);
            case KAKAO : return new KakaoOAuth2User(attributes);

            default : throw new InvalidProviderException("[ERROR] 지원하지 않은 소셜 플랫폼입니다");
        }
    }
}
