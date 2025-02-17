package dobong.life.userInfo;

import dobong.life.enums.SocialType;
import dobong.life.util.exception.InvalidProviderException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(SocialType socialType, Map<String, Object> attributes){
        switch(socialType){
            case GOOGLE : return new GoogleOAuth2User(attributes);
            case NAVER : return new NaverOAuth2User(attributes);
            case KAKAO : return new KakaoOAuth2User(attributes);

            default : throw new InvalidProviderException("[ERROR] 지원하지 않는 소셜 로그인입니다");
        }
    }
}
